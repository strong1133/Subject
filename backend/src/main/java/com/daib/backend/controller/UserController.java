package com.daib.backend.controller;

import com.daib.backend.domain.board.User;
import com.daib.backend.dto.UserRequestDto;
import com.daib.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
