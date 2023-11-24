package com.gitittech.paygo.springservicegateway.controllers;

/**
 *
 * @author ambag
 */
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SettingsControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void should_get_settings_as_a_user() throws Exception {

        mockMvc.perform(get("/settings")
                .param("isSearch", "false")
                .param("filter", "")
                .param("page", "0")
                .param("size", "20")
                .param("direction", "DESC")
                .param("property", "id"))                   
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((jsonPath("$.content.length()", is(6))))
                .andReturn();
    }
    
}
