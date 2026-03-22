package org.springsecurity.recipeblogapplication.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springsecurity.recipeblogapplication.entity.Post;
import org.springsecurity.recipeblogapplication.entity.User;
import org.springsecurity.recipeblogapplication.repository.UserRepo;
import org.springsecurity.recipeblogapplication.service.PostService;
import org.springsecurity.recipeblogapplication.service.UserService;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostService postService;

    @ModelAttribute
    public void commonUser(Principal principal, Model model){
        if(principal != null){
            String email = principal.getName();
            User user = userRepo.findByEmail(email);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/signin")
    public String login(){
        return "login";
    }

    @GetMapping("/user/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/user/home")
    public String home(){
        return "home";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session){
        userService.saveUser(user);
        session.setAttribute("msg", "Регистрация прошла успешно!");
        return "redirect:/register";
    }

}
