package com.JM.BootCRUD.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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