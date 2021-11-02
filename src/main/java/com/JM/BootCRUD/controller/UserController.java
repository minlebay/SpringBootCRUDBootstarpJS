package com.JM.BootCRUD.controller;

import com.JM.BootCRUD.model.User;
import com.JM.BootCRUD.service.RoleService;
import com.JM.BootCRUD.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public ModelAndView main(Principal principal,
                             ModelAndView modelAndView) {
        modelAndView.addObject("user", userService.getByUsername(principal.getName()));
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView console(Principal principal,
                                ModelAndView modelAndView) {
        modelAndView.addObject("currentUser", userService.getByUsername(principal.getName()));
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("selectableRoles", roleService.getAll());
        return modelAndView;
    }
    @PostMapping("/admin")
    public RedirectView create(@ModelAttribute("user") User user,
                               Long[] roleIds) {
        userService.edit(user, roleIds);
        return new RedirectView("/admin");
    }

    @RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView edit(@ModelAttribute("user") User user,
                             Long[] roleIds,
                             @PathVariable("id") Long id) {
        userService.edit(userService.getById(id), roleIds);
        return new RedirectView("/admin");
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new RedirectView("/admin");
    }
}