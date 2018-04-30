package com.lavreniuk.gymcounter.controllers;

import com.lavreniuk.gymcounter.domain.User;
import com.lavreniuk.gymcounter.enums.ResponseMessages;
import com.lavreniuk.gymcounter.exceptions.SuchEmailAlreadyExistsException;
import com.lavreniuk.gymcounter.exceptions.SuchUsernameAlreadyExistsException;
import com.lavreniuk.gymcounter.responses.ErrorResponse;
import com.lavreniuk.gymcounter.responses.Response;
import com.lavreniuk.gymcounter.responses.SuccessResponse;
import com.lavreniuk.gymcounter.security.CustomAuthentication;
import com.lavreniuk.gymcounter.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author taras.lavreniuk
 * @date 15.04.18.
 */
@RestController
public class LoginController extends BaseController {

    @Autowired
    private CustomAuthentication customAuthentication;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response registerUser(@RequestBody User user) {
        System.out.println(user);
        System.out.println(user.getUsername().getClass().getName());
        System.out.println(user.getEmail().getClass().getName());
        System.out.println(user.getPassword().getClass().getName());
        Long userId;
        try {
            userId = userService.createUser(user.getUsername(), user.getPassword(), user.getEmail());
        } catch (SuchUsernameAlreadyExistsException e) {
            e.printStackTrace();
            return new ErrorResponse(HttpStatus.BAD_REQUEST, ResponseMessages.UserWithSuchUsernameAlreadyExistError.getMessage());
        } catch (SuchEmailAlreadyExistsException e) {
            e.printStackTrace();
            return new ErrorResponse(HttpStatus.BAD_REQUEST, ResponseMessages.UserWithSuchEMailAlreadyExistError.getMessage());
        }
        return new SuccessResponse<>(HttpStatus.OK, userId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response login(@RequestBody User user) {
        String token;
        System.out.println(user);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        try {
            token = customAuthentication.attemptAuthentication(user.getUsername(), user.getPassword());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ErrorResponse(HttpStatus.BAD_REQUEST, ResponseMessages.AuthenticationFailed.getMessage());
        }
        return new SuccessResponse<>(HttpStatus.OK, token);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response updateUser(@RequestBody User user) {
        User result = userService.updateUser(user);
        return new SuccessResponse<>(HttpStatus.OK, result);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        System.out.println(getCurrentUserId());
        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }

}
