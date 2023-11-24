package com.gitittech.paygo.springservicegateway.error;

import com.gitittech.paygo.commons.exceptions.AuthorizationException;
import com.gitittech.paygo.commons.exceptions.DuplicateRecordException;
import com.gitittech.paygo.commons.exceptions.NotFoundException;
import com.gitittech.paygo.commons.exceptions.ThirdPartyServiceProviderException;
import com.gitittech.paygo.paymentmethod.exceptions.BadBankAccountException;
import com.gitittech.paygo.paymentmethod.exceptions.PaymentMethodNotFoundException;
import com.gitittech.paygo.transaction.exceptions.InsufficientFundException;
import com.gitittech.paygo.user.exceptions.NoPINException;
import com.gitittech.paygo.user.exceptions.AuthenticationException;
import com.gitittech.paygo.user.exceptions.BVNVerificationException;
import com.gitittech.paygo.user.exceptions.EmailVerificationPendingException;
import com.gitittech.paygo.user.exceptions.InvalidOtpException;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({NotFoundException.class, NoResultException.class})
    public ResponseEntity<Error> handleNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(6000, ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Error> handleAuthException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Error(6001, "Authentication failed"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleBadRequestException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error(6002, ex.getMessage()));
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<Error> handleForbiddenException(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Error(6003, ex.getMessage()));
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<Error> handleDuplicateException(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new Error(6005, ex.getMessage()));
    }

    @ExceptionHandler(EmailVerificationPendingException.class)
    public ResponseEntity<Error> handleVerificationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Error(6006, ex.getMessage()));
    }

    @ExceptionHandler(BVNVerificationException.class)
    public ResponseEntity<Error> handleBadBVNException(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Error(6007, ex.getMessage()));
    }

    @ExceptionHandler(BadBankAccountException.class)
    public ResponseEntity<Error> handleBadBankException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(6008, ex.getMessage()));
    }

    @ExceptionHandler(PaymentMethodNotFoundException.class)
    public ResponseEntity<Error> handlePaymentMethodNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(6009, ex.getMessage()));
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Error> handleUnAuthorizationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Error(6010, ex.getMessage()));
    }

    @ExceptionHandler(NoPINException.class)
    public ResponseEntity<Error> handleNoPINException(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Error(6011, ex.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Error> handleExpiredJWTException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Error(6012, "Your session has expired"));
    }

    @ExceptionHandler(InsufficientFundException.class)
    public ResponseEntity<Error> handleInsufficientBalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new Error(6013, "Insufficient balance"));
    }

    @ExceptionHandler(ThirdPartyServiceProviderException.class)
    public ResponseEntity<Error> handleBadGatewayException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(new Error(502, ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
