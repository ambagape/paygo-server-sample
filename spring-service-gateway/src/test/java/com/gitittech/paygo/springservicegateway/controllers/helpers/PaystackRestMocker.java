/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.springservicegateway.controllers.helpers;

import com.github.javafaker.Faker;
import com.gitittech.paygo.commons.BearerTokenMaker;
import com.gitittech.paygo.integrations.dtos.NewCustomerReq;
import com.gitittech.paygo.integrations.dtos.CustomerIdentificationResponse;
import com.gitittech.paygo.integrations.payments.IPaystackRest;
import com.gitittech.paygo.user.dtos.User;
import java.io.IOException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Call;
import retrofit2.Response;

/**
 *
 * @author ambag
 */
public class PaystackRestMocker {

    private static final Faker faker = new Faker();
    
    @Value("${paystack.secret}")
    private static String paystackSecret;

    public static IPaystackRest addMocks(IPaystackRest paystackService, User user) throws IOException {
        Call<CustomerIdentificationResponse> mockCall = mock(Call.class);
        Response<CustomerIdentificationResponse> response = Response.success(new CustomerIdentificationResponse(faker.name().name(),
                new CustomerIdentificationResponse.Data(faker.number().digits(5),
                        faker.name().username(),
                        faker.internet().emailAddress()
                )));
        when(mockCall.execute()).thenReturn(response);
        NewCustomerReq req = new NewCustomerReq(user.getEmail(), user.getFirstName(), user.getLastName());
        when(paystackService.createCustomer("application/json", BearerTokenMaker.create(paystackSecret), req)).thenReturn(mockCall);
        
        return paystackService;
    }
}
