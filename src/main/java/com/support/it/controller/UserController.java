package com.support.it.controller;

import com.support.it.entity.User;
import com.support.it.exception.ITSupportAppException;
import com.support.it.model.UserDTO;
import com.support.it.repository.UserRepository;
import com.support.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping(value = "/createUser")
    public String createNewAccount(@Valid @RequestBody UserDTO userDTO) throws ITSupportAppException {
        String registrationMessage = userService.createUser(userDTO);
        return registrationMessage;
    }


    @GetMapping(value = "/users")
    public String findUsers() {
        List<User> allUsers = userRepository.findAll();
        return String.valueOf(allUsers);
    }
}
