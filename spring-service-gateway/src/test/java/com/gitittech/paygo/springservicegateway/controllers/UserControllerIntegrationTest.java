package com.gitittech.paygo.springservicegateway.controllers;

import com.gitittech.paygo.user.api.IUserReadRepository;
import com.gitittech.paygo.user.dtos.User;
import com.gitittech.paygo.user.impl.IUserCommandRepository;
import com.google.gson.Gson;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.regex.Pattern;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserControllerIntegrationTest extends AbstractIntegrationTest {

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
        user = new User();
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setBvn(faker.number().digits(11));
        user.setPhone(faker.phoneNumber().phoneNumber());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setMiddleName(faker.name().lastName());
    }

    @AfterEach
    public void tearDown() {
        smtpServer.reset();
        smtpServer.stop();
    }

    @Test
    public void Should_not_be_able_to_login_without_verification() throws Exception {
        setupClass();

        final var jsonUser = gson.toJson(user);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assert.assertEquals(1, smtpServer.getReceivedMessages().length);
        smtpServer.reset();
        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "\"email\":\"" + user.getEmail() + "\","
                        + "\"password\":\"" + user.getPassword() + "\""
                        + "\n}")
        )
                .andExpect(status().isForbidden())
                .andReturn();

        final var msg = smtpServer.getReceivedMessages()[0];
        final var matcher = Pattern.compile("\\d+").matcher(msg.getContent().toString());
        assertTrue(matcher.find());

        final var code = matcher.group();
        mockMvc.perform(put("/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "\"email\":\"" + user.getEmail() + "\","
                        + "\"code\":\"" + code + "\""
                        + "\n}")
        )
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "\"email\":\"" + user.getEmail() + "\","
                        + "\"password\":\"" + user.getPassword() + "\""
                        + "\n}")
        )
                .andExpect(status().isOk())
                .andReturn();

        tearDown();
    }

    @Test
    public void Should_be_to_view_profile() throws Exception {

        login("ambagape@live.com", "nonstop12", List.of());

        mockMvc.perform(get("/users/profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.createdBy", is("ambagape@live.com")))
                .andExpect(jsonPath("$.bvn", is(notNullValue())))
                .andExpect(jsonPath("$.bvnVerified", is(false)))
                .andExpect(jsonPath("$.email", is("ambagape@live.com")))
                .andExpect(jsonPath("$.phone", is(notNullValue())))
                .andExpect(jsonPath("$.firstName", is(notNullValue())))
                .andExpect(jsonPath("$.lastName", is(notNullValue())))
                .andExpect(jsonPath("$.middleName", is(notNullValue())))
                .andExpect(jsonPath("$.customerCode", is(notNullValue())))
                .andExpect(jsonPath("$.address", is(nullValue())))
                .andExpect(jsonPath("$.city", is(nullValue())))
                .andExpect(jsonPath("$.state", is(nullValue())))
                .andExpect(jsonPath("$.country", is(nullValue())))
                .andExpect(jsonPath("$.dateOfBirth", is(nullValue())))
                .andExpect(jsonPath("$.gender", is(nullValue())))
                .andExpect(jsonPath("$.nextOfKinName", is(nullValue())))
                .andExpect(jsonPath("$.nextOfKinAddress", is(nullValue())))
                .andExpect(jsonPath("$.accountId", is(notNullValue())))
                .andExpect(jsonPath("$.balance", is(0.0)))
                .andExpect(jsonPath("$.password", is(notNullValue())))
                .andExpect(jsonPath("$.nextOfKinPhone", is(nullValue())))
                .andExpect(jsonPath("$.completedTour", is(false)))
                .andExpect(jsonPath("$.disabled", is(false)));

        tearDown();
    }

    @Test
    public void Should_be_able_to_reset_my_password() throws Exception {

        mockMvc.perform(post("/reset-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "email":"ambagape@live.com"                           
                         }""")
        )
                .andExpect(status().isOk())
                .andReturn();

        final var msg = smtpServer.getReceivedMessages()[0];

        final var matcher = Pattern.compile("\\d+").matcher(msg.getContent().toString());
        assertTrue(matcher.find());
        final var code = matcher.group();
        mockMvc.perform(patch("/users/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                          "email":"register@live.com",
                          "code":"343434",
                          "password":"nonstop123"
                         }
                         """)
        )
                .andExpect(status().isForbidden())
                .andReturn();

        mockMvc.perform(patch("/users/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""                         
                        {
                        "email":"register@live.com",
                            "code":"${code}",
                            "password":"nonstop123"
                        }""")
        )
                .andExpect(status().isOk())
                .andReturn();

        tearDown();
    }

    @Test
    public void Should_be_able_to_create_pin() throws Exception {

        login("ambagape@live.com", "nonstop12", List.of());

        mockMvc.perform(post("/users/create-pin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "pin": "456734"                           
                         }""")
        )
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(get("/users/profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.pin", is(notNullValue())));

        mockMvc.perform(post("/users/create-pin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "oldPin":"450734",
                           "pin":"450984"                           
                         }""")
        )
                .andExpect(status().isForbidden())
                .andReturn();

        mockMvc.perform(post("/users/create-pin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "oldPin":"456734",
                           "pin":"450984"                           
                         }""")
        )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser("ambagape@live.com")
    public void should_be_able_to_do_bvn_verification() throws Exception {
        login("ambagape@live.com", "nonstop12", List.of());

        mockMvc.perform(post("/users/verify-bvn")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                            "account_number": "0111111111",
                            "bvn": "22222222221",
                            "bank_code": "007",
                            "first_name": "Uchenna",
                            "last_name": "Okoro",
                            "country":"NG",
                            "type":"bank_account"               
                         }""")
        )
                .andExpect(status().is(Matchers.anyOf(is(200), is(502))))
                .andReturn();

        mockMvc.perform(get("/users/profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bvnVerified", is(false)));                
        
        mockMvc.perform(post("/paystack/webhook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                              "event": "customeridentification.success",
                              "data": {
                                "customer_id": "9387490384",
                                "customer_code": "CUS_9yndxxmxdncsrg0",
                                "email": "ambagape@live.com",
                                "identification": {
                                  "country": "NG",
                                  "type": "bank_account",
                                  "bvn": "200*****677",
                                  "account_number": "012****789",
                                  "bank_code": "007"
                                }
                              }
                         }""")
        )
                .andExpect(status().isOk())
                .andReturn();
        
        
        mockMvc.perform(get("/users/profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bvnVerified", is(true)))
                .andExpect(jsonPath("$.bvn", is(notNullValue())));
    }
}
