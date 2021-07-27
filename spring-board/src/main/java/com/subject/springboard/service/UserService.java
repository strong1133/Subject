package com.subject.springboard.service;


import com.subject.springboard.board.User;
import com.subject.springboard.config.auth.PrincipalDetails;
import com.subject.springboard.config.util.SignupValidator;
import com.subject.springboard.dto.UserRequestDto;
import com.subject.springboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    // 현재 로그인한 유저 정보 반환
    public User curUserInfo(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long userId = principalDetails.getUser().getId();
        return userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("해당 id의 유저가 없습니다.")
        );
    }



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
