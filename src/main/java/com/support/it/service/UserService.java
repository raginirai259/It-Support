package com.support.it.service;

import com.support.it.entity.User;
import com.support.it.exception.ITSupportAppException;
import com.support.it.model.UserDTO;

public interface UserService {

    User findUserByEmail(String email);


    boolean isUser(User user);


    void save(User userEntity);

    String createUser(UserDTO userDTO) throws ITSupportAppException;
}
