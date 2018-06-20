package com.lavreniuk.gymcounter.controllers;

import com.lavreniuk.gymcounter.domain.Training;
import com.lavreniuk.gymcounter.enums.ExceptionsMessages;
import com.lavreniuk.gymcounter.enums.ResponseMessages;
import com.lavreniuk.gymcounter.exceptions.NoSuchTrainingException;
import com.lavreniuk.gymcounter.responses.ErrorResponse;
import com.lavreniuk.gymcounter.responses.Response;
import com.lavreniuk.gymcounter.responses.SuccessResponse;
import com.lavreniuk.gymcounter.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author taras
 * @date 17.06.18.
 */

@RestController
@RequestMapping("/telegram")
public class TelegramController extends BaseController {

    @Autowired
    private TelegramService telegramService;

    @RequestMapping(value = "/training", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getTrainings(@RequestHeader("telegram-nickname") String telegramNickname) {
        return new SuccessResponse<>(HttpStatus.OK, telegramService.getUserTrainings(telegramNickname));
    }

    @RequestMapping(value = "/training/{trainingId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getTraining(@RequestHeader("telegram-nickname") String telegramNickname,
                                @PathVariable("trainingId") String trainingId) {
        Training training;
        try {
            training = telegramService.getUserTraining(trainingId);
        } catch (NoSuchTrainingException e) {
            e.printStackTrace();
            return new ErrorResponse(HttpStatus.NOT_FOUND, ResponseMessages.NoSuchTrainingException.getMessage());
        }
        return new SuccessResponse<>(HttpStatus.OK, training);
    }

    @RequestMapping(value = "/exercise", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getExercises(@RequestHeader("telegram-nickname") String telegramNickname) {
        return new SuccessResponse<>(HttpStatus.OK, telegramService.getUserExercises(telegramNickname));
    }

    @RequestMapping(value = "/exercise/{exerciseId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getExercise(@RequestHeader("telegram-nickname") String telegramNickname,
                                @PathVariable("exerciseId") String exerciseId) {
        return new SuccessResponse<>(HttpStatus.OK, telegramService.getUserExercise(telegramNickname, exerciseId));
    }

    @RequestMapping(value = "/user_param", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getUserParams(@RequestHeader("telegram-nickname") String telegramNickname) {
        return new SuccessResponse<>(HttpStatus.OK, telegramService.getUserParams(telegramNickname));
    }

    @RequestMapping(value = "/user_param/{paramId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getUserParam(@RequestHeader("telegram-nickname") String telegramNickname,
                                @PathVariable("paramId") String paramId) {
        return new SuccessResponse<>(HttpStatus.OK, telegramService.getUserParam(telegramNickname, paramId));
    }


}
