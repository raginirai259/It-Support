package com.support.it.service.impl;

import com.support.it.entity.User;
import com.support.it.service.UserService;
import com.support.it.exception.ITSupportAppException;
import com.support.it.exception.InvalidEmailException;
import com.support.it.exception.InvalidPasswordException;
import com.support.it.exception.UserIdAlreadyPresentException;
import com.support.it.model.UserDTO;
import com.support.it.model.UserRole;
import com.support.it.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public boolean isUser(User user) {
        return user.getRoles().contains("USER");
    }

    @Override
    public void save(User userEntity) {
        userEntity.setActive(1);
        userEntity.setRoles(Collections.singleton(UserRole.USER));
        userRepository.save(userEntity);
    }

    @Override
    public String createUser(UserDTO userDTO) throws ITSupportAppException {
        validateUser(userDTO);
        User exists = findUserByEmail(userDTO.getEmail());

        if (exists != null) {
            throw new UserIdAlreadyPresentException("RegistrationService.USERID_PRESENT");
        }

        User userEntity = new User();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setName(userDTO.getName());
        userEntity.setPassword(userDTO.getPassword());
        save(userEntity);
        return "UserRepository.REGISTRATION_SUCCESS";
    }

    private void validateUser(UserDTO userDTO) throws ITSupportAppException {

        if (!isValidEmail(userDTO.getEmail()))
            throw new InvalidEmailException("RegistrationService.INVALID_EMAIL");

        if (!isValidPassword(userDTO.getPassword()))
            throw new InvalidPasswordException("RegistrationService.INVALID_PASSWORD");
    }

    public boolean isValidPassword(String password) {
        boolean b1 = false;

        String regex1 = "^[a-zA-Z\\d]{8,15}+$";

        Pattern pattern2 = Pattern.compile(regex1);
        Matcher matcher2 = pattern2.matcher(password);
        if (matcher2.matches())
            b1 = true;

        return b1;
    }

    public boolean isValidEmail(String email) {
        boolean b1 = false;
        String regex2 = "^[A-Za-z\\d+_.-]+@(.+)$";

        Pattern pattern5 = Pattern.compile(regex2);
        Matcher matcher5 = pattern5.matcher(email);
        if (matcher5.matches())
            b1 = true;
        return b1;
    }

}
