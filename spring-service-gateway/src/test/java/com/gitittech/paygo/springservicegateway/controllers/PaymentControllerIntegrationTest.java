package com.gitittech.paygo.springservicegateway.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gitittech.paygo.user.api.IUserReadRepository;
import com.gitittech.paygo.user.dtos.User;
import com.gitittech.paygo.user.impl.IUserCommandRepository;
import com.google.gson.Gson;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.AfterEach;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PaymentControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserCommandRepository commandRepository;

    @Autowired
    private IUserReadRepository readRepository;

    private final Gson gson = new Gson();

    GreenMail smtpServer = new GreenMail(new ServerSetup(3025, null, "smtp"));

    private User user;

    @BeforeEach
    public void setupClass() {
        smtpServer.start();               
    }

    @AfterEach
    public void tearDown() {
        smtpServer.reset();
        smtpServer.stop();
    }

    @Test
    public void Should_credit_account_with_fundeed_amount() throws Exception {
        setupClass();
        login("ambagape@live.com", "nonstop12", List.of());

        final var result = mockMvc.perform(get("/payments/card")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.authorization_url", is(notNullValue())))
                .andExpect(jsonPath("$.data.access_code", is(notNullValue())))
                .andExpect(jsonPath("$.data.reference", is(notNullValue())))
                .andReturn();
        final var stringContent = result.getResponse().getContentAsString();

        final var reference = new ObjectMapper().readTree(stringContent).get("data.reference");

        mockMvc.perform(post("/paystack/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "event": "charge.success",
                           "data": {
                             "id": 302961,
                             "domain": "live",
                             "status": "success",
                             "reference":"${reference}",                                                       
                             "amount": 10000,
                             "message": null,
                             "gateway_response": "Approved by Financial Institution",
                             "paid_at": "2016-09-30T21:10:19.000Z",
                             "created_at": "2016-09-30T21:09:56.000Z",
                             "channel": "card",
                             "currency": "NGN",
                             "ip_address": "41.242.49.37",
                             "metadata": 0,
                             "log": {
                               "time_spent": 16,
                               "attempts": 1,
                               "authentication": "pin",
                               "errors": 0,
                               "success": false,
                               "mobile": false,
                               "input": [],
                               "channel": null,
                               "history": [
                                 {
                                   "type": "input",
                                   "message": "Filled these fields: card number, card expiry, card cvv",
                                   "time": 15
                                 },
                                 {
                                   "type": "action",
                                   "message": "Attempted to pay",
                                   "time": 15
                                 },
                                 {
                                   "type": "auth",
                                   "message": "Authentication Required: pin",
                                   "time": 16
                                 }
                               ]
                             },
                             "fees": null,
                             "customer": {
                               "id": 68324,
                               "first_name": "BoJack",
                               "last_name": "Horseman",
                               "email": "ambagape@live.com",
                               "customer_code": "CUS_9yndxxmxdncsrg0",
                               "phone": null,
                               "metadata": null,
                               "risk_action": "default"
                             },
                             "authorization": {
                               "authorization_code": "AUTH_f5rnfq9p",
                               "bin": "539999",
                               "last4": "8877",
                               "exp_month": "08",
                               "exp_year": "2020",
                               "card_type": "mastercard DEBIT",
                               "bank": "Guaranty Trust Bank",
                               "country_code": "NG",
                               "brand": "mastercard",
                               "account_name": "BoJack Horseman"
                             },
                             "plan": {}
                           }
                         }"""))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(get("/users/profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(5000)));
    }

    @Test
    public void Should_credit_account_with_funded_amount_even_without_existing_initialization() throws Exception {
        setupClass();
        mockMvc.perform(post("/paystack/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "event": "charge.success",
                           "data": {
                                   mockMvc.perform(get("/users/profile")
                             "id": 302961,
                             "domain": "live",
                             "status": "success",
                             "reference":"${reference}",                                                       
                             "amount": 10000,
                             "message": null,
                             "gateway_response": "Approved by Financial Institution",
                             "paid_at": "2016-09-30T21:10:19.000Z",
                             "created_at": "2016-09-30T21:09:56.000Z",
                             "channel": "card",
                             "currency": "NGN",
                             "ip_address": "41.242.49.37",
                             "metadata": 0,
                             "log": {
                               "time_spent": 16,
                               "attempts": 1,
                               "authentication": "pin",
                               "errors": 0,
                               "success": false,
                               "mobile": false,
                               "input": [],
                               "channel": null,
                               "history": [
                                 {
                                   "type": "input",
                                   "message": "Filled these fields: card number, card expiry, card cvv",
                                   "time": 15
                                 },
                                 {
                                   "type": "action",
                                   "message": "Attempted to pay",
                                   "time": 15
                                 },
                                 {
                                   "type": "auth",
                                   "message": "Authentication Required: pin",
                                   "time": 16
                                 }
                               ]
                             },
                             "fees": null,
                             "customer": {
                               "id": 68324,
                               "first_name": "BoJack",
                               "last_name": "Horseman",
                               "email": "ambagape@live.com",
                               "customer_code": "CUS_9yndxxmxdncsrg0",
                               "phone": null,
                               "metadata": null,
                               "risk_action": "default"
                             },
                             "authorization": {
                               "authorization_code": "AUTH_f5rnfq9p",
                               "bin": "539999",
                               "last4": "8877",
                               "exp_month": "08",
                               "exp_year": "2020",
                               "card_type": "mastercard DEBIT",
                               "bank": "Guaranty Trust Bank",
                               "country_code": "NG",
                               "brand": "mastercard",
                               "account_name": "BoJack Horseman"
                             },
                             "plan": {}
                           }
                         }"""))
                .andExpect(status().isOk())
                .andReturn();

        login("ambagape@live.com", "nonstop12", List.of());
        mockMvc.perform(get("/users/profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(5000)));

    }
}
