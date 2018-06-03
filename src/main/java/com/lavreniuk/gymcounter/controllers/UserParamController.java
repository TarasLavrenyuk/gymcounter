package com.lavreniuk.gymcounter.controllers;

import com.lavreniuk.gymcounter.domain.Param;
import com.lavreniuk.gymcounter.domain.UserParam;
import com.lavreniuk.gymcounter.domain.dto.UserParamDto;
import com.lavreniuk.gymcounter.responses.Response;
import com.lavreniuk.gymcounter.responses.SuccessResponse;
import com.lavreniuk.gymcounter.service.UserParamService;
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
 * @date 03.06.18.
 */
@RestController
public class UserParamController extends BaseController {

    @Autowired
    private UserParamService userParamService;

    @RequestMapping(value = "/addParam", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response addParam(@RequestBody Param param) {
        userParamService.addParam(param);
        return new SuccessResponse<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteParam", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response deleteParam(@RequestBody Param param) {
        userParamService.deleteParam(param);
        return new SuccessResponse<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/saveUserParam", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response saveUserParam(@RequestBody List<UserParam> userParams) {
        userParamService.saveUserParams(userParams);
        return new SuccessResponse<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserParam", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response saveUserParam(@RequestBody Param param) {
        List<UserParamDto> userParams = userParamService.getUserParams(param.getParamId(), getCurrentUserId());
        return new SuccessResponse<>(HttpStatus.OK, userParams);
    }

    @RequestMapping(value = "/getUserParams", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response saveUserParam() {
        List<Param> userParams = userParamService.getUserParams(getCurrentUserId());
        return new SuccessResponse<>(HttpStatus.OK, userParams);
    }

}
