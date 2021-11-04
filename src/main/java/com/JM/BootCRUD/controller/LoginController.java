package com.JM.BootCRUD.controller;

import com.JM.BootCRUD.model.User;
import com.JM.BootCRUD.service.RoleService;
import com.JM.BootCRUD.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
public class LoginController {

    public LoginController() {}

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView main(ModelAndView modelAndView) {
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView console(ModelAndView modelAndView) {
        return modelAndView;
    }
}