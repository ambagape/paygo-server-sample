package com.gitittech.paygo.user.impl;

import com.gitittech.paygo.commons.BearerTokenMaker;
import com.gitittech.paygo.integrations.dtos.PaystackBooleanResponse;
import com.gitittech.paygo.commons.JwtTokenUtil;
import com.gitittech.paygo.commons.OTPUtil;
import com.gitittech.paygo.commons.exceptions.DuplicateRecordException;
import com.gitittech.paygo.commons.exceptions.NotFoundException;
import com.gitittech.paygo.commons.exceptions.ThirdPartyServiceProviderException;
import com.gitittech.paygo.entities.*;
import com.gitittech.paygo.integrations.dtos.BvnVerificationRequest;
import com.gitittech.paygo.message.api.INotification;
import com.gitittech.paygo.message.impl.NotificationTemplateService;
import com.gitittech.paygo.user.api.IUser;
import com.gitittech.paygo.user.api.IUserReadRepository;
import com.gitittech.paygo.user.api.IUserViewReadRepository;
import com.gitittech.paygo.integrations.dtos.CustomerIdentificationResponse;
import com.gitittech.paygo.integrations.dtos.NewCustomerReq;
import com.gitittech.paygo.integrations.payments.IPaystackRest;
import com.gitittech.paygo.paymentmethod.dtos.NewBankAccountReq;
import com.gitittech.paygo.paymentmethod.dtos.NewBankAccountResponse;
import com.gitittech.paygo.user.dtos.User;
import com.gitittech.paygo.user.dtos.UserWithToken;
import com.gitittech.paygo.user.dtos.requests.PasswordUpdateRequest;
import com.gitittech.paygo.user.dtos.requests.RegisterRequest;
import com.gitittech.paygo.user.exceptions.*;
import com.gitittech.paygo.user.mappers.IUserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import retrofit2.Response;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IUserImpl implements IUser {

    final private IUserReadRepository userReadRepository;
    final private IUserViewReadRepository userViewReadRepository;

    private final UserServiceUtil serviceUtil;
    private final OTPUtil otpUtil;
    private final NotificationTemplateService templateService;
    private final INotification emailService;
    
    final private IUserCommandRepository userCommandRepository;
    final private JwtTokenUtil jwtTokenUtil;

    final private KafkaTemplate<String, User> kafkaTemplate;
    

    private IPaystackRest paystack;
    
    @Value("${paystack.secret}")
    private String paystackSecret;

    @Autowired
    public IUserImpl(IPaystackRest paystack, IUserCommandRepository userCommandRepository,
            IUserReadRepository userReadRepository, IUserViewReadRepository userViewReadRepository,
            UserServiceUtil serviceUtil, OTPUtil otpUtil, NotificationTemplateService templateService,
            @Qualifier("email") INotification emailService,            
            KafkaTemplate<String, User> kafkaTemplate,
            JwtTokenUtil jwtTokenUtil)
            throws Exception {
        this.paystack = paystack;
        this.userCommandRepository = userCommandRepository;
        this.userReadRepository = userReadRepository;
        this.userViewReadRepository = userViewReadRepository;
        this.serviceUtil = serviceUtil;
        this.otpUtil = otpUtil;
        this.templateService = templateService;
        this.emailService = emailService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void confirmEmail(String email, String code) throws Throwable {
        Optional<JpaUser> optionalUser = userReadRepository.findExistingUser(email, null, null);
        optionalUser.orElseThrow(() -> new NotFoundException("user not found"));
        var user = optionalUser.get();
        if (user.getEmailVerified()) {
            throw new DuplicateRecordException("This account is already verified");
        }
        if (otpUtil.generateOTP(user.getId()) == Integer.parseInt(code)) {
            user.setIsEmailVerified(true);
            userCommandRepository.save(user);
        } else {
            throw new InvalidOtpException("Invalid OTP");
        }
    }

    @Override
    public void requestEmailVerification(String email) throws Throwable {
        final var user = userViewReadRepository.findExistingUser(email, null, null)
                .orElseThrow(() -> new NotFoundException("user not found"));
        serviceUtil.sendConfirmationEmail(IUserMapper.INSTANCE.toUser(user));
    }

    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdate)
            throws Throwable {
        JpaUser savedUser = userReadRepository.findExistingUser(passwordUpdate.getEmail(), null, null).orElseThrow(() -> new NotFoundException("user not found"));
        if (passwordUpdate.getOldPassword() != null && savedUser.getPassword().equals(serviceUtil.hashIt(passwordUpdate.getOldPassword()))) {
            savedUser.setPassword(serviceUtil.hashIt(passwordUpdate.getNewPassword()));
            userCommandRepository.save(savedUser);
        } else if (passwordUpdate.getCode().equals(Integer.toString(otpUtil.generateOTP(savedUser.getId())))) {
            savedUser.setPassword(serviceUtil.hashIt(passwordUpdate.getNewPassword()));
            userCommandRepository.save(savedUser);
        } else {
            throw new AuthenticationException("Password could not be updated");
        }
    }

    @Override
    public void resetPassword(String email) throws Throwable {
        final var user = userViewReadRepository.findExistingUser(email, null, null)
                .orElseThrow(() -> new NotFoundException("user not found"));
        serviceUtil.sendPasswordResetEmail(IUserMapper.INSTANCE.toUser(user));
    }

    @Override
    public void confirmPhone(String id, String code) throws Throwable {
        final var optional = userReadRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        final var foundUser = optional.get();
        if (this.otpUtil.generateOTP(foundUser.getId()) == Integer.parseInt(code)) {
            foundUser.setIsPhoneVerified(true);
            userCommandRepository.save(foundUser);
        } else {
            throw new InvalidOtpException("Invalid OTP");
        }
    }

    @Override
    public void requestPhoneVerification(String phone) throws Throwable {
        JpaUser stageUser = userReadRepository.findExistingUser(null, phone, null).orElseThrow(() -> new NotFoundException("user not found"));
        serviceUtil.sendConfirmationPhone(IUserMapper.INSTANCE.toUser(stageUser));
    }

    @Override
    public void notifyUserOfLogin(User user) throws Throwable {
        Map<String, String> args = new HashMap<>();
        args.put(INotification.RECIPEINT_NAME_PARAM, user.getFirstName());
        var msg = templateService.loadFromTemplate("b27681a6-00e2-11ed-b939-0242ac120002", user, args);
        emailService.send(msg);
    }

    @Override
    public void logout(User user) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateUser(User user) throws Throwable {
        final var userView = getContextUser();
        userView.orElseThrow(() -> new NotFoundException("user not found"));
        final var jpaUserOptional = userReadRepository.findById(userView.get().getId());
        jpaUserOptional.ifPresent(foundUser -> {
            if (user.getAddress() != null && !user.getAddress().isBlank()) {
                foundUser.setAddress(user.getAddress());
            }
            if (user.getCity() != null && !user.getCity().isBlank()) {
                foundUser.setCity(user.getCity());
            }
            if (user.getCountry() != null && !user.getCountry().isBlank()) {
                foundUser.setCountry(user.getCountry());
            }
            if (user.getDateOfBirth() != null) {
                foundUser.setDateOfBirth(user.getDateOfBirth());
            }
            if (user.getFirstName() != null && !user.getFirstName().isBlank()) {
                foundUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null && !user.getLastName().isBlank()) {
                foundUser.setLastName(user.getLastName());
            }
            if (user.getMiddleName() != null && !user.getMiddleName().isBlank()) {
                foundUser.setMiddleName(user.getMiddleName());
            }
            if (user.getNextOfKinAddress() != null && !user.getNextOfKinAddress().isBlank()) {
                foundUser.setNextOfKinAddress(user.getNextOfKinAddress());
            }
            if (user.getNextOfKinName() != null && !user.getNextOfKinName().isBlank()) {
                foundUser.setNextOfKinName(user.getNextOfKinName());
            }
            if (user.getNextOfKinPhone() != null && !user.getNextOfKinPhone().isBlank()) {
                foundUser.setNextOfKinPhone(user.getNextOfKinPhone());
            }
            if (user.getPhone() != null && !user.getPhone().isBlank()) {
                foundUser.setPhone(user.getPhone());
            }
            if (user.getState() != null && !user.getState().isBlank()) {
                foundUser.setState(user.getState());
            }
            if (user.getGender() != null && !user.getGender().isBlank()) {
                foundUser.setGender(user.getGender());
            }
            userCommandRepository.save(foundUser);
        });
    }

    @Override
    public void updateUser(JpaUser user) {
        userCommandRepository.save(user);
    }

    @Override
    public User findUser(String userId) throws Throwable {
        final var optional = userReadRepository
                .findById(userId);
        if (optional.isEmpty()) {
            throw new NotFoundException("user not found");
        }
        return IUserMapper.INSTANCE.toUser(optional.get());
    }

    @Override
    public User signup(RegisterRequest registerRequest) throws Throwable {
        final var optionalJpaUser = userReadRepository
                .findExistingUser(registerRequest.email(), registerRequest.phone(), registerRequest.bvn());
        if (optionalJpaUser.isPresent()) {
            throw new DuplicateRecordException("User already exists");
        }
        final var jpaUser = new JpaUser();
        jpaUser.setPhone(registerRequest.phone());
        jpaUser.setBvn(registerRequest.bvn());
        jpaUser.setEmail(registerRequest.email());
        jpaUser.setFirstName(registerRequest.firstName());
        jpaUser.setLastName(registerRequest.lastName());
        jpaUser.setMiddleName(registerRequest.middleName());
        jpaUser.setPassword(serviceUtil.hashIt(registerRequest.password()));
        jpaUser.setCreatedBy(jpaUser.getEmail());
        jpaUser.setModifiedBy(jpaUser.getEmail());
        CustomerIdentificationResponse cir = createUserOnPaystack(IUserMapper.INSTANCE.toUser(jpaUser));
        jpaUser.setCustomerCode(cir.data.customer_code);
        final var savedUser = userCommandRepository
                .save(jpaUser);
        final var newUser = IUserMapper.INSTANCE.toUser(savedUser);
        this.kafkaTemplate.send("new-user", newUser);
        this.serviceUtil.sendConfirmationEmail(newUser);
        return newUser;
    }

    @Override
    public CustomerIdentificationResponse createUserOnPaystack(User user) throws Throwable {
        NewCustomerReq req = new NewCustomerReq(user.getEmail(), user.getFirstName(), user.getLastName());
        Response<CustomerIdentificationResponse> res
                = this.paystack.createCustomer("application/json", BearerTokenMaker.create(this.paystackSecret), req).execute();
        if (res.errorBody() != null) {
            ThirdPartyServiceProviderException.throwUpstreamServerError(res.code(), res.errorBody().string());
        }
        CustomerIdentificationResponse cir = res.body();
        return cir;
    }

    @Override
    public PaystackBooleanResponse requestBVNVerification(BvnVerificationRequest request) throws Throwable {
        final var jpaUserOptional = this.getContextUser();
        final var jpaUser = jpaUserOptional.get();
        jpaUser.setBvn(request.bvn());
        userCommandRepository.save(jpaUser);
        Response<PaystackBooleanResponse> res
                = this.paystack.verifyUserIdentity("application/json", BearerTokenMaker.create(this.paystackSecret), jpaUser.getCustomerCode(), request)
                        .execute();
        if (res.errorBody() != null) {
            ThirdPartyServiceProviderException.throwUpstreamServerError(res.code(), res.errorBody().string());
        }
        return res.body();
    }

    @Override
    public UserWithToken signin(String email, String phone, String bvn, String password)
            throws Throwable {
        String encryptedPassword = serviceUtil.hashIt(password);
        final var foundUser = userViewReadRepository.findExistingUser(email, phone, bvn).orElseThrow(() -> new NotFoundException("user not found"));
        if (foundUser != null && foundUser.getPassword().equals(encryptedPassword)) {
            final var updateUserOptional = userReadRepository.findById(foundUser.getId());

            AtomicReference<UserWithToken> userNode = new AtomicReference<>();
            final var updatedUser = updateUserOptional.get();

            if (!updatedUser.getEmailVerified()) {
                serviceUtil.sendConfirmationEmail(IUserMapper.INSTANCE.toUser(updatedUser));
                throw new EmailVerificationPendingException("Check your inbox to confirm your email address");
            } else if (updatedUser.getIsDisabled()) {
                throw new NotFoundException("This account has been disabled.");
            } else {
                final var userWithToken = IUserMapper.INSTANCE.toUserWithToken(foundUser);
                final String authToken = jwtTokenUtil.generateToken(foundUser.getEmail());
                userWithToken.setAuthorization(authToken);
                userNode.set(userWithToken);
                this.notifyUserOfLogin(IUserMapper.INSTANCE.toUser(foundUser));
            }

            return userNode.get();
        } else {
            throw new AuthenticationException("Invalid login credentials");
        }
    }

    @Override
    public void confirmBvnVerification(CustomerIdentificationResponse cir) throws Throwable {
        JpaUser user = userReadRepository.findByCustomerCode(cir.data.customer_code).orElseThrow(() -> new NotFoundException("user not found"));
        user.setIsBvnVerified(true);
        this.userCommandRepository.save(user);
        if (user.getFundingAccount() == null) {
            createDedicatedNuban(user);
        }
    }

    @Override
    public Optional<JpaUser> getContextUser() {
        final var user = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        final var jpaUserOptional = userReadRepository
                .findExistingUser(((JpaUser) user).getUsername(), null, null);
        return jpaUserOptional;
    }

    @Override
    public Optional<UserWithBalanceView> getContextUserWithBalance() {
        final var user = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        final var userOptional = userViewReadRepository
                .findExistingUser(((JpaUser) user).getUsername(), null, null);
        return userOptional;
    }

    @Override
    public void setPIN(String pin, String oldPin) throws Throwable {
        final var jpaUserOptional = getContextUser();
        if (jpaUserOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        final var jpaUser = jpaUserOptional.get();

        if (oldPin == null && jpaUser.getPin() == null) {
            jpaUser.setPin(serviceUtil.hashIt(pin));
        } else if (jpaUser.getPin() != null && serviceUtil.hashIt(oldPin).equals(jpaUser.getPin())) {
            jpaUser.setPin(serviceUtil.hashIt(pin));
        } else {
            throw new NoPINException("Wrong pin was provided");
        }
        this.userCommandRepository.save(jpaUser);
    }
   
    @Override
    public User findUserByCustomerCode(String customerCode) throws Throwable {
        final var optional = userViewReadRepository.findByCustomerCode(customerCode);
        if (optional.isEmpty()) {
            throw new NotFoundException("user not found");
        }
        return IUserMapper.INSTANCE
                .toUser(optional.get());
    }

    @Override
    public void disableUser(String userId, Boolean value) {
        userReadRepository.findById(userId).ifPresent((user) -> {
            user.setIsDisabled(value);
            userCommandRepository.save(user);
        });
    }

    @Override
    public Page<User> getUsers(Boolean isSearch, String filter, Integer page, Integer size, String sort, List<String> properties) throws Throwable {
        var propertiesArr = new String[properties.size()];
        properties.toArray(propertiesArr);
        return userViewReadRepository.getUsers(isSearch, filter, page, size, Sort.Direction.fromString(sort), propertiesArr)
                .map(u -> IUserMapper.INSTANCE.toUser(u));
    }
    
     @Async
    private void createDedicatedNuban(JpaUser user) throws Exception {
        NewBankAccountReq req = new NewBankAccountReq(user.getCustomerCode(), "providus-bank");
        Response<NewBankAccountResponse> res = this.paystack.createAccount("application/json", BearerTokenMaker.create(this.paystackSecret), req).execute();
        CompletableFuture.runAsync(() -> {
            NewBankAccountResponse nbar = res.body();
            JpaFundingAccount jpaFundingAccount = new JpaFundingAccount();
            jpaFundingAccount.setAccountName(nbar.data.account_name);
            jpaFundingAccount.setAccountNo(nbar.data.account_number);
            jpaFundingAccount.setBankCode(Long.toString(nbar.data.bank.id));
            jpaFundingAccount.setBankName(nbar.data.bank.name);
            jpaFundingAccount.setCurrency(nbar.data.currency);
            user.setFundingAccount(jpaFundingAccount);
            userCommandRepository.save(user);
        });
    }

}
