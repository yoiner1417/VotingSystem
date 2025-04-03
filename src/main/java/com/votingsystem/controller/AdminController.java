package com.votingsystem.controller;

import com.votingsystem.service.ActivityService;
import com.votingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/panel")
    public String adminPanel() {
        return "admin_panel";
    }

    @GetMapping("/usuarios")
    public String gestionarUsuarios(Model model) {
        model.addAttribute("usuarios", userService.findAll());
        return "gestionar_usuarios";
    }

    @GetMapping("/actividades")
    public String gestionarActividades(Model model) {
        model.addAttribute("actividades", activityService.findAll());
        return "gestionar_actividades";
    }
}
