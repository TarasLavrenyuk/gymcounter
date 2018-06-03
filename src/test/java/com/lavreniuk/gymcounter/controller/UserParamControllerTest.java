package com.lavreniuk.gymcounter.controller;

import com.lavreniuk.gymcounter.domain.Param;
import com.lavreniuk.gymcounter.domain.User;
import com.lavreniuk.gymcounter.domain.UserParam;
import com.lavreniuk.gymcounter.utils.GeneratorUtil;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author taras
 * @date 03.06.18.
 */
public class UserParamControllerTest extends BaseControllerTest {

    //    @Test
    public void testAddParam() throws Exception {
        Param param = new Param(GeneratorUtil.generate(), "weight", "kg");
        MvcResult mvcResult = mockMvc
                .perform(put("/addParam")
                        .headers(getHeaders("taras.lavreniuk", "123456"))
                        .content(OBJECT_MAPPER.writeValueAsString(param))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //    @Test
    public void testSaveUserParam() throws Exception {
        String paramId = "713003249b9b452094f42394171b6139";
        List<UserParam> userParams = new ArrayList<>();
        userParams.add(new UserParam(GeneratorUtil.generate(), paramId, 91., new Date()));
        userParams.add(new UserParam(GeneratorUtil.generate(), paramId, 92., new Date()));
        userParams.add(new UserParam(GeneratorUtil.generate(), paramId, 93., new Date()));
        MvcResult mvcResult = mockMvc
                .perform(put("/saveUserParam")
                        .headers(getHeaders("taras.lavreniuk", "123456"))
                        .content(OBJECT_MAPPER.writeValueAsString(userParams))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetUserParams() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get("/getUserParams")
                        .headers(getHeaders("taras.lavreniuk", "123456"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetUserParam() throws Exception {
        Param param = new Param();
        param.setParamId("713003249b9b452094f42394171b6139");
        MvcResult mvcResult = mockMvc
                .perform(get("/getUserParam")
                        .headers(getHeaders("taras.lavreniuk", "123456"))
                        .content(OBJECT_MAPPER.writeValueAsString(param))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }


}
