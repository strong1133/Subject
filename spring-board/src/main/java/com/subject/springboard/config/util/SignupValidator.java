package com.subject.springboard.config.util;


import com.subject.springboard.board.User;
import com.subject.springboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Configuration
public class SignupValidator {

    private final UserRepository userRepository;


    //Id - 정규식
    public static boolean usernameValid(String usename){
        return Pattern.matches("^[A-za-z0-9]{5,15}", usename);
    }

    //Id - 중복검사

    public  boolean usernameDupCheck(String username){
        User foundedUsername = userRepository.findByUsername(username);
        return foundedUsername == null;
    }

    //PW - 정규식
    public static boolean passwordValid(String  username,String password){
        if(password.contains(username)){
            return false;
        }
        return Pattern.matches("^[A-za-z0-9]{5,15}", password);
    }
}
