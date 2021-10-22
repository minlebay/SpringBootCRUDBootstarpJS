package com.JM.BootCRUD.controller;

import com.JM.BootCRUD.model.Role;
import com.JM.BootCRUD.model.User;
import com.JM.BootCRUD.service.RoleService;
import com.JM.BootCRUD.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/addUser")
    public ModelAndView addPage(ModelAndView modelAndView) {
        modelAndView.addObject("user", new User());
        modelAndView.addObject("selectableRoles", roleService.getAll());
        return modelAndView;
    }

    @PostMapping("/admin/addUser")
    public ModelAndView addUser(@ModelAttribute("user") User user,
                                Long[] roleIds,
                                ModelAndView modelAndView) {
        userService.add(user, roleIds);
        modelAndView.setViewName("redirect:/admin/console");
        return modelAndView;
    }

    @GetMapping("/admin/editUser")
    public ModelAndView editPage(@RequestParam(name = "id") Long id,
                                 ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.getById(id));
        modelAndView.addObject("selectableRoles", roleService.getAll());
        modelAndView.setViewName("/admin/editUser");
        return modelAndView;
    }

    @PostMapping("/admin/editUser")
    public ModelAndView editUser(@ModelAttribute("user") User user,
                                 Long[] roleIds,
                                 ModelAndView modelAndView) {
        userService.edit(user, roleIds);
        modelAndView.setViewName("redirect:/admin/console");
        return modelAndView;
    }

    @GetMapping("/admin/deleteUser")
    public ModelAndView deleteUser(@ModelAttribute("user") User user,
                                   ModelAndView modelAndView) {
        userService.delete(user.getId());
        modelAndView.setViewName("redirect:/admin/console");
        return modelAndView;
    }
}
