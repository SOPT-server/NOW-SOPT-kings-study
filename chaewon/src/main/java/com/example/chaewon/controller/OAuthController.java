package com.example.chaewon.controller;

import com.example.chaewon.service.dto.MemberFindDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuthController {

    @GetMapping("/login")
    public String home() {
        return "/home";
    }

    @GetMapping("/")
    public String profile(Model model, HttpSession session) {
        MemberFindDto member = (MemberFindDto) session.getAttribute("member");
        if (member != null) {
            model.addAttribute("name", member.name());
            model.addAttribute("email", member.email());
            model.addAttribute("picture", member.picture());
        }
        return "/profile";
    }

}
