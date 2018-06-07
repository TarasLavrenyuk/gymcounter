package com.lavreniuk.gymcounter.controller;

import com.lavreniuk.gymcounter.domain.Set;
import com.lavreniuk.gymcounter.domain.Training;
import com.lavreniuk.gymcounter.filter.TrainingFilter;
import com.lavreniuk.gymcounter.utils.GeneratorUtil;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author taras
 * @date 07.06.18.
 */
public class SyncControllerTest extends BaseControllerTest {

    //    @Test
    public void testSaveData() throws Exception {
        List<Training> trainings = new LinkedList<>();
        String trainingId1 = GeneratorUtil.generate();
        Training training1 = new Training(trainingId1, "Monday training", new Date());
        List<Set> sets1 = new LinkedList<>();
        sets1.add(new Set(GeneratorUtil.generate(), "fd2b040c2fb946ff96113696a5332a73", trainingId1, new Date(), 5, 15.));
        sets1.add(new Set(GeneratorUtil.generate(), "fd2b040c2fb946ff96113696a5332a73", trainingId1, new Date(), 5, 25.));
        sets1.add(new Set(GeneratorUtil.generate(), "fd2b040c2fb946ff96113696a5332a73", trainingId1, new Date(), 5, 35.));
        sets1.add(new Set(GeneratorUtil.generate(), "fd2b040c2fb946ff96113696a5332a73", trainingId1, new Date(), 5, 40.));
        sets1.add(new Set(GeneratorUtil.generate(), "64cac8ee959b413a904d4859e0686405", trainingId1, new Date(), 10, 50.));
        sets1.add(new Set(GeneratorUtil.generate(), "64cac8ee959b413a904d4859e0686405", trainingId1, new Date(), 8, 60.));
        sets1.add(new Set(GeneratorUtil.generate(), "64cac8ee959b413a904d4859e0686405", trainingId1, new Date(), 6, 70.));
        sets1.add(new Set(GeneratorUtil.generate(), "64cac8ee959b413a904d4859e0686405", trainingId1, new Date(), 4, 75.));
        training1.addSets(sets1);

        String trainingId2 = GeneratorUtil.generate();
        Training training2 = new Training(trainingId2, "Monday training", new Date());
        List<Set> sets2 = new LinkedList<>();
        sets2.add(new Set(GeneratorUtil.generate(), "ff262f4dde39432fb2d0f61e5e95f865", trainingId2, new Date(), 5, 15.));
        sets2.add(new Set(GeneratorUtil.generate(), "ff262f4dde39432fb2d0f61e5e95f865", trainingId2, new Date(), 5, 25.));
        sets2.add(new Set(GeneratorUtil.generate(), "ff262f4dde39432fb2d0f61e5e95f865", trainingId2, new Date(), 5, 35.));
        sets2.add(new Set(GeneratorUtil.generate(), "ff262f4dde39432fb2d0f61e5e95f865", trainingId2, new Date(), 5, 40.));
        sets2.add(new Set(GeneratorUtil.generate(), "7d0b35610f634a2da17d92da6caff990", trainingId2, new Date(), 10, 50.));
        sets2.add(new Set(GeneratorUtil.generate(), "7d0b35610f634a2da17d92da6caff990", trainingId2, new Date(), 8, 60.));
        sets2.add(new Set(GeneratorUtil.generate(), "7d0b35610f634a2da17d92da6caff990", trainingId2, new Date(), 6, 70.));
        sets2.add(new Set(GeneratorUtil.generate(), "7d0b35610f634a2da17d92da6caff990", trainingId2, new Date(), 4, 75.));
        training2.addSets(sets2);

        trainings.add(training1);
        trainings.add(training2);


        MvcResult mvcResult = mockMvc
                .perform(put("/saveData")
                        .headers(getHeaders("taras.lavreniuk", "123456"))
                        .content(OBJECT_MAPPER.writeValueAsString(trainings))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
    }

    //    @Test
    public void testRetrieveData() throws Exception {
        Date from = new Date();
        from.setDate(1514764800);
//        from.setYear(118);
//        from.setMonth(1);
        Date to = new Date();
        to.setDate(1546300800);
//        to.setYear(119);
//        to.setMonth(1);
        TrainingFilter trainingFilter = new TrainingFilter(from, to);

        MvcResult mvcResult = mockMvc
                .perform(post("/retrieveData")
                        .headers(getHeaders("taras.lavreniuk", "123456"))
                        .content(OBJECT_MAPPER.writeValueAsString(new TrainingFilter()))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(result);
    }
}
