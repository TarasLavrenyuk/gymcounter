package com.lavreniuk.gymcounter.controllers;

import com.lavreniuk.gymcounter.domain.Training;
import com.lavreniuk.gymcounter.filter.TrainingFilter;
import com.lavreniuk.gymcounter.responses.Response;
import com.lavreniuk.gymcounter.responses.SuccessResponse;
import com.lavreniuk.gymcounter.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author taras
 * @date 04.06.18.
 */
@RestController
public class SyncController extends BaseController {

    @Autowired
    private SyncService syncService;

    @RequestMapping(value = "/saveData", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response saveData(@RequestBody List<Training> trainings) {
        syncService.save(trainings);
        return new SuccessResponse<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response retrieveData(@RequestBody TrainingFilter filter) {
        System.out.println(filter.getFrom());
        System.out.println(filter.getTo());
        List<Training> retrieve = syncService.retrieve(filter);
        return new SuccessResponse<>(HttpStatus.OK, retrieve);
    }


}