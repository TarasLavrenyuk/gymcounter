package com.lavreniuk.gymcounter.controller;

import com.lavreniuk.gymcounter.domain.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author taras
 * @date 28.04.18.
 */
public class LoginControllerTest extends BaseControllerTest {

//    @Test
    public void test() throws Exception {
        Map<String, String> content = new HashMap<>();
        content.put("username", "taras.lavreniuk");
        content.put("password", "123456");
        System.out.println(OBJECT_MAPPER.writeValueAsString(content));
    }

//    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setWeight(80.);
        user.setHeight(186.);
        MvcResult mvcResult = mockMvc
                .perform(post("/updateUser")
                        .headers(getHeaders("taras.lavreniuk", "123456"))
                        .content(OBJECT_MAPPER.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testLogin() throws Exception {
        User user = new User("taras.lavreniuk", "123456");
        MvcResult mvcResult = mockMvc
                .perform(post("/login")
                        .header("Content-Type", "application/json")
                        .content(OBJECT_MAPPER.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
