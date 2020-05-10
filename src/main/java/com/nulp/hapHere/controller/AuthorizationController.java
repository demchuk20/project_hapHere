package com.nulp.hapHere.controller;

import com.nulp.hapHere.entity.User;
import com.nulp.hapHere.logger.Log;
import com.nulp.hapHere.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class AuthorizationController {
    private UserRepo userRepo;

    @Autowired
    public AuthorizationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Log
    @GetMapping("/sign-in")
    public String singIn() {
        return "sign-in";
    }

    @Log
    @GetMapping("/sign-up")
    public String singUp() {
        return "sign-up";
    }

    @Log
    @PostMapping("/sign-up")
    public String addUser(User user, Map<String, Object> model) {
        if (userRepo.findByEmail(user.getEmail()).isEmpty()) {
            userRepo.save(user);
            return "redirect:/sign-in";
        } else {
            model.put("message", "User exists!");
            return "sign-up";
        }
    }

    @Log
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
