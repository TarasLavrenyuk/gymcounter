package com.lavreniuk.gymcounter.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author taras
 * @date 17.06.18.
 */
public class TelegramControllerTest extends BaseControllerTest {

    @Test
    public void testGetTrainings() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/telegram/training")
                        .headers(getHeadersWithParams("telegram-nickname", "taraslavrenyuk"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetTraining() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/telegram/training/922ef48a828b43cbbb7a293078df24ba")
                        .headers(getHeadersWithParams("telegram-nickname", "taraslavrenyuk"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetExercises() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/telegram/exercise")
                        .headers(getHeadersWithParams("telegram-nickname", "taraslavrenyuk"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetExercise() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/telegram/exercise/64cac8ee959b413a904d4859e0686405")
                        .headers(getHeadersWithParams("telegram-nickname", "taraslavrenyuk"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetParams() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/telegram/user_param/")
                        .headers(getHeadersWithParams("telegram-nickname", "taraslavrenyuk"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void testGetParam() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/telegram/user_param/495e4945f87549dd87e8b491d02ec702")
                        .headers(getHeadersWithParams("telegram-nickname", "taraslavrenyuk"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
