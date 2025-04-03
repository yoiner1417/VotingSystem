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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contrasena, Model model) {
        Optional<User> user = userRepository.findByCorreo(correo);
        if (user.isPresent() && user.get().getContrasena().equals(contrasena)) {
            // In a real application, you should use proper password hashing and session management
            return "redirect:/foro";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

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

