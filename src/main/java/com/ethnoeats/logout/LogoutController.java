package com.ethnoeats.controller;

import com.ethnoeats.logout.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    private final LogoutService logoutService;

    @Autowired
    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        logoutService.logout(request);
        return "redirect:/login"; // Redirect to login page after logout
    }
}
