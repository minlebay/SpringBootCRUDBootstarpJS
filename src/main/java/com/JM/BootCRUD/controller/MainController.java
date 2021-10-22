package com.JM.BootCRUD.controller;

import com.JM.BootCRUD.model.Role;
import com.JM.BootCRUD.model.User;
import com.JM.BootCRUD.service.RoleService;
import com.JM.BootCRUD.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private final UserService userService;
    private final RoleService roleService;

    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView main(Principal principal, ModelAndView modelAndView) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        User user = userService.getByUsername(principal.getName());
        modelAndView.addObject("messages", messages);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/admin/console")
    public ModelAndView console(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("roles", roleService.getAll());
        return modelAndView;
    }
}
