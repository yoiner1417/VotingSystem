package com.votingsystem.controller;

import com.votingsystem.model.User;
import com.votingsystem.repository.UserRepository;
import com.votingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "login";
    }

    // El método @PostMapping("/login") ha sido eliminado para evitar conflictos con Spring Security
    // Spring Security manejará automáticamente la autenticación

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute User user) {
        user.setRol("estudiante");
        userService.save(user);
        return "redirect:/login";
    }
}
