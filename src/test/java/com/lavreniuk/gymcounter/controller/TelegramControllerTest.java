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

}
