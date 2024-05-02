package com.example.seojin.controller;

import com.example.seojin.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

  @GetMapping("/home")
  public String home() {
    return "/main.html";
  }

  // 메인 화면 - 게시판 목록
  @GetMapping("/")
  public String postList(HttpSession session, Model model) {

    // 세션에서 사용자 정보 꺼내기
    SessionUser user = (SessionUser) session.getAttribute("user");
    if (user != null) {
      model.addAttribute("name", user.getName());
      model.addAttribute("email", user.getEmail());

    }

    return "output";
  }
}
