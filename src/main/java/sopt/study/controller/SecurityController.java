package sopt.study.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/login")
    public String home() {
        return "/home.html";
    }

    @GetMapping("/")
    public String profile(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("picture", session.getAttribute("picture"));
        return "/profile.html";
    }
}
