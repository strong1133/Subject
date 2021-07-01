package com.daib.backend.controller;


import com.daib.backend.config.security.UserDetailsImpl;
import com.daib.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long maxPageNum = postService.maxPageNum();
        if (userDetails != null) {
            String username = userDetails.getUsername();
            model.addAttribute("username", username);
            model.addAttribute("maxPageNum", maxPageNum);
            return "index";
        }
        model.addAttribute("message","null" );
        model.addAttribute("maxPageNum", maxPageNum);
        return "index";
    }

    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){return "signup";}

    @GetMapping("/detail")
    public String detail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            model.addAttribute("username", username);
            return "detail";
        }
        model.addAttribute("message", "null");
        return "detail";
    }
}
