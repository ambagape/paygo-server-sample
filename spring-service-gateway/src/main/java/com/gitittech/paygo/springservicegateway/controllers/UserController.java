package com.gitittech.paygo.springservicegateway.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.gitittech.paygo.entities.UserWithBalanceView;
import com.gitittech.paygo.user.api.IUser;
import com.gitittech.paygo.user.dtos.User;
import com.gitittech.paygo.user.dtos.UserWithToken;
import com.gitittech.paygo.integrations.dtos.BvnVerificationRequest;
import com.gitittech.paygo.user.dtos.requests.CreatePinRequest;
import com.gitittech.paygo.user.dtos.requests.DisableUserRequest;
import com.gitittech.paygo.user.dtos.requests.EmailVerificationRequest;
import com.gitittech.paygo.user.dtos.requests.LoginRequest;
import com.gitittech.paygo.user.dtos.requests.PasswordUpdateRequest;
import com.gitittech.paygo.user.dtos.requests.RegisterRequest;
import com.gitittech.paygo.user.dtos.requests.ResetPasswordRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    private final IUser userService;

    @Autowired
    public UserController(IUser userService) {
        this.userService = userService;
    }

    @GetMapping("/users/profile")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserWithBalanceView> getContextUser() throws Exception, Throwable {
        var user = userService.getContextUserWithBalance();
        return ResponseEntity.ok(user.get());
    }    
    
    
    @PostMapping("/users/verify-bvn")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity requestBVNVerification(@RequestBody @Valid BvnVerificationRequest request) throws Throwable {
        final var response = userService.requestBVNVerification(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/users/password")    
    public ResponseEntity updatePassword(@RequestBody @Valid PasswordUpdateRequest passwordUpdate) throws Exception, Throwable {
        userService.updatePassword(passwordUpdate);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/register")    
    public ResponseEntity<User> signup(@RequestBody @Valid RegisterRequest user) throws Exception, Throwable {
        var signedUpUser = userService.signup(user);
        return ResponseEntity.ok(signedUpUser);
    }

    @PostMapping("/auth")
    public ResponseEntity<UserWithToken> signin(@RequestBody @Valid LoginRequest login) throws Throwable {
        final var userWithToken = userService.signin(login.email(), login.phone(), login.bvn(),
                login.password());
        return ResponseEntity.ok(userWithToken);
    }

    @GetMapping("/verify/{email}")
    public ResponseEntity requestEmailVerification(@PathVariable String email) throws Throwable {
        userService.requestEmailVerification(email);
        return ResponseEntity.ok().build();
    }    

    @PutMapping("/verify")
    public ResponseEntity confirmEmail(@RequestBody @Valid EmailVerificationRequest verificationRequest) throws Exception, Throwable {
        userService.confirmEmail(verificationRequest.email(), verificationRequest.code());
        return ResponseEntity.ok().build();
    }    

    @PostMapping("/reset-password")    
    public ResponseEntity resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) throws Throwable {
        var email = resetPasswordRequest.email();
        userService.resetPassword(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/create-pin")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity createPIN(@RequestBody @Valid CreatePinRequest pin) throws Exception, Throwable {
        userService.setPIN(pin.getPin(), pin.getOldPin());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/{userId}/disable/{isDisable}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity disableUser(@PathVariable String userId, @RequestBody @Valid DisableUserRequest isDisable) {
        this.userService.disableUser(userId, isDisable.isDisable());
        return ResponseEntity.noContent().build();
    }

    
    @PutMapping("/users/{userId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity updateUser(@PathVariable String userId, @RequestBody User user) throws Exception, Throwable {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/users/{userId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<User> findUser(@PathVariable String userId) throws Exception, Throwable {
        final var user = userService.findUser(userId);
        return ResponseEntity.ok(user);
    }
}
