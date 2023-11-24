/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.springservicegateway.config;

import com.gitittech.paygo.integrations.payments.IFlutterwave;
import com.gitittech.paygo.integrations.payments.IPaystackRest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author ambag
 */
@Configuration
public class RetrofitConfig {
    
    @Bean
    public IPaystackRest iPaystackRest() {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IPaystackRest.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                 .build();
        return retrofit.create(IPaystackRest.class);        
    }
    
    @Bean
    public IFlutterwave iFlutterwave() {
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IFlutterwave.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                 .build();
        return retrofit.create(IFlutterwave.class);        
    }
}
