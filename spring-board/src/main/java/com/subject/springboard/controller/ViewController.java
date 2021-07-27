package com.subject.springboard.controller;


import com.subject.springboard.board.User;
import com.subject.springboard.service.PostService;
import com.subject.springboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
//        Long maxPageNum = postService.maxPageNum();
//        User user = userService.curUserInfo(authentication);
//        if (user != null) {
//            String username = user.getUsername();
//            model.addAttribute("username", username);
//            model.addAttribute("maxPageNum", maxPageNum);
//            return "index.html";
//        }
//        model.addAttribute("message","null" );
//        model.addAttribute("maxPageNum", maxPageNum);
        return "index.html";
    }

    @GetMapping("/login")
    public String login(){return "login.html";}

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @GetMapping("/signup")
    public String signup(){return "signup.html";}

    @GetMapping("/detail")
    public String detail(Model model, Authentication authentication) {
        User user = userService.curUserInfo(authentication);
        if (user != null) {
            String username = user.getUsername();
            model.addAttribute("username", username);
            return "detail.html";
        }
        model.addAttribute("message", "null");
        return "detail.html";
    }
}
