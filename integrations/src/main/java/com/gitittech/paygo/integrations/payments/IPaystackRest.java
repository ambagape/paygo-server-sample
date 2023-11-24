package com.gitittech.paygo.integrations.payments;

import com.gitittech.paygo.integrations.dtos.AcctResolutionRes;
import com.gitittech.paygo.integrations.dtos.AllBankRequest;
import com.gitittech.paygo.integrations.dtos.BankChargeReq;
import com.gitittech.paygo.integrations.dtos.BvnVerificationRequest;
import com.gitittech.paygo.integrations.dtos.CustomerIdentificationResponse;
import com.gitittech.paygo.integrations.dtos.NewCustomerReq;
import com.gitittech.paygo.integrations.dtos.PaymentGatewayTransaction;
import com.gitittech.paygo.integrations.dtos.PaystackBooleanResponse;
import com.gitittech.paygo.integrations.dtos.PaystackTransferRecipientReq;
import com.gitittech.paygo.integrations.dtos.PaystackTransferRecipientRes;
import com.gitittech.paygo.paymentmethod.dtos.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface IPaystackRest {

    String ENDPOINT = "https://api.paystack.co/";      

    @POST("dedicated_account")
    Call<NewBankAccountResponse> createAccount(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body NewBankAccountReq req);

    @POST("customer/{customerCode}/identification")
    Call<PaystackBooleanResponse> verifyUserIdentity(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Path("customerCode") String customerCode, @Body BvnVerificationRequest req);

    @POST("transferrecipient")
    Call<PaystackTransferRecipientRes> createPaystackRecipient(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body PaystackTransferRecipientReq ptr);

    @GET("transferrecipient/{id}")
    Call<PaystackTransferRecipientRes> getPaystackRecipient(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Path("id") String id);

    @GET("bank/resolve")
    Call<AcctResolutionRes> resolveAccountNumber(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Query("account_number") String accountNumber, @Query("bank_code") String bankCode);

    @POST("transfer")
    Call<TransferResponse> transfer(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body TransferRequest transferRequest);

    @POST("customer")
    Call<CustomerIdentificationResponse> createCustomer(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body NewCustomerReq transferRequest);

    @POST("transaction/charge_authorization")
    Call<PaymentGatewayTransaction> chargeCard(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body CardChargeReq charge);

    @POST("charge")
    Call<PaymentGatewayTransaction> chargeAcct(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body BankChargeReq charge);

    @POST("charge/submit_otp")
    Call<PaymentGatewayTransaction> submitOtp(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body SubmitOtpReq req);

    @POST("charge/submit_birthday")
    Call<PaymentGatewayTransaction> submitBirthday(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Body SubmitDOBReq req);

    @GET("transaction/verify/{reference}")
    Call<PaymentGatewayTransaction> verifyTransaction(@Header("Content-Type") String contentType, @Header("Authorization") String token, @Path("reference") String reference);

    @GET("bank")
    Call<AllBankRequest> getBanks(@Header("Content-Type") String contentType, @Header("Authorization") String token);

    @POST("transaction/initialize")
    Call<InitializeVerificationResponse> initializeTransaction(@Header("Content-Type") String contentType, @Header("Authorization") String token,  @Body InitializeVerificationRequest req);
}
