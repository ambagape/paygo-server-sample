package com.gitittech.paygo.user.api;

import com.gitittech.paygo.entities.JpaUser;
import com.gitittech.paygo.entities.UserWithBalanceView;
import com.gitittech.paygo.integrations.dtos.CustomerIdentificationResponse;
import com.gitittech.paygo.integrations.dtos.PaystackBooleanResponse;
import com.gitittech.paygo.user.dtos.User;
import com.gitittech.paygo.user.dtos.UserWithToken;
import com.gitittech.paygo.user.dtos.payments.PaymentMethod;
import com.gitittech.paygo.integrations.dtos.BvnVerificationRequest;
import com.gitittech.paygo.user.dtos.requests.PasswordUpdateRequest;
import com.gitittech.paygo.user.dtos.requests.RegisterRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IUser {
    void requestEmailVerification(String email) throws Throwable;

    void confirmPhone(String id, String code) throws Throwable;

    void requestPhoneVerification(String phone) throws Throwable;

    void notifyUserOfLogin(User user) throws Throwable;

    void logout(User user);

    User findUser(String userId) throws Throwable;

    Optional<JpaUser> getContextUser()
            throws Throwable;

    Optional<UserWithBalanceView> getContextUserWithBalance() throws Throwable;
            
    void updatePassword(PasswordUpdateRequest passwordUpdate)
            throws Throwable;

    void resetPassword(String email) throws Throwable;

    User signup(RegisterRequest user) throws Throwable;

    UserWithToken signin(String email, String phone, String bvn, String password) throws Throwable;

    void updateUser(User user) throws Throwable;
    
    void updateUser(JpaUser user);

    void confirmBvnVerification(CustomerIdentificationResponse cir) throws Throwable;

    CustomerIdentificationResponse createUserOnPaystack(User user) throws Throwable;

    PaystackBooleanResponse requestBVNVerification(BvnVerificationRequest request) throws Throwable;

    void setPIN(String pin, String oldPin) throws Throwable;

    void confirmEmail(String email, String code)
            throws Throwable;
      
    void disableUser(String userId, Boolean value);

    User findUserByCustomerCode(String customerCode) throws Throwable;

    Page<User> getUsers(Boolean isSearch, String filter, Integer page, Integer size, String sort, List<String> properties) throws Throwable;

}