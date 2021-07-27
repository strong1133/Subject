package com.subject.springboard.controller;


import com.subject.springboard.board.User;
import com.subject.springboard.dto.UserRequestDto;
import com.subject.springboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/user")
    public List<User> userList(){
        return userService.userList();
    }

    @PostMapping("/signup")
    public String signupUser(@RequestBody UserRequestDto userRequestDto){
        userService.registerUser(userRequestDto);
        return "회원가입 완료";
    }

}
