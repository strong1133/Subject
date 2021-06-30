package com.daib.backend.controller;


import com.daib.backend.config.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            model.addAttribute("username", username);
            return "index";
        }
        model.addAttribute("message","null" );
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
}
