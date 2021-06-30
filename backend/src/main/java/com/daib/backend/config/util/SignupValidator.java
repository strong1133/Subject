package com.daib.backend.config.util;

import com.daib.backend.domain.board.User;
import com.daib.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
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
        Optional<User> foundUsername = userRepository.findByUsername(username);
        return foundUsername.isEmpty();
    }

    //PW - 정규식
    public static boolean passwordValid(String  username,String password){
        if(password.contains(username)){
            return false;
        }
        return Pattern.matches("^[A-za-z0-9]{5,15}", password);
    }
}
