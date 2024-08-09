package com.hotel.hotel.controller;

import com.hotel.hotel.dto.LoginRequest;
import com.hotel.hotel.dto.Response;
import com.hotel.hotel.entity.User;
import com.hotel.hotel.service.CloudinaryService;
import com.hotel.hotel.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @PostMapping("/register")
    public ResponseEntity<Response> register (@RequestBody User user){
        Response response = userService.register(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> Login (@RequestBody LoginRequest loginRequest){
        Response response = userService.login(loginRequest);
        logger.info("user login  {}", response);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/confirm-email/{confirmationToken}")
    public ResponseEntity<Response> ConfirmEmail(@PathVariable String confirmationToken) {
        Response response = userService.confirmEmail(confirmationToken);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
