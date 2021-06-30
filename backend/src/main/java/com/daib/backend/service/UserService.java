package com.daib.backend.service;

import com.daib.backend.config.util.SignupValidator;
import com.daib.backend.domain.board.User;
import com.daib.backend.dto.UserRequestDto;
import com.daib.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SignupValidator signupValidator;
    private static final String SCERET_KEY = "asd123cdf*sd23%^&f";

    //회원가입
    public User registerUser(UserRequestDto userRequestDto){
        String username = userRequestDto.getUsername();
        String rawPassword = userRequestDto.getPassword();


        if (!signupValidator.usernameDupCheck(username)){
            throw new IllegalArgumentException("이미 존재하는 username 입니다.");
        }
        if (!SignupValidator.usernameValid(username)){
            throw new IllegalArgumentException("올바른 형식의 username을 입력해주세요.");
        }
        if (!SignupValidator.passwordValid(username,rawPassword)){
            throw new IllegalArgumentException("올바른 형식의 username을 입력해주세요.");
        }
        System.out.println("username, password => 유효성 검사 통과");

        String encodedPassword = passwordEncoder.encode(rawPassword);

        userRequestDto.setPassword(encodedPassword);
        User user = new User(userRequestDto);
        userRepository.save(user);
        System.out.println("회원가입완료");
        return user;
    }

    public List<User> userList(){
        return userRepository.findAll();
    }
}
