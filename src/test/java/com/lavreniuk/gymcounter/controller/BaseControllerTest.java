package com.lavreniuk.gymcounter.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lavreniuk.gymcounter.domain.User;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author taras
 * @date 29.04.18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
@WebAppConfiguration
public class BaseControllerTest {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    protected HttpHeaders getAuthorizationHeaders(String username, String password) throws Exception {
        User user = new User(username, password);
        MvcResult mvcResult = mockMvc
                .perform(post("/login")
                        .header("Content-Type", "application/json")
                        .content(OBJECT_MAPPER.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        String token = obj.getString("object");
        HttpHeaders httpHeaders = getHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        return httpHeaders;
    }

    protected HttpHeaders getHeaders() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return httpHeaders;
    }

    protected HttpHeaders getHeadersWithParams(String... param) throws Exception {
        HttpHeaders result = getHeaders();
        if (param.length < 2 || param.length % 2 == 1) {
            return result;
        }
        for (int i = 0; i < param.length; i += 2) {
            result.add(param[i], param[i + 1]);
        }

        return result;
    }

    @Test
    public void name() throws Exception {

    }
}
