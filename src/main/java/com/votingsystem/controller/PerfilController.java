package com.votingsystem.controller;

import com.votingsystem.model.User;
import com.votingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PerfilController {

    @Autowired
    private UserService userService;

    @GetMapping("/perfil")
    public String mostrarPerfil(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByEmail(username);
        model.addAttribute("user", user);
        return "perfil";
    }

    @PostMapping("/perfil")
    public String actualizarPerfil(@RequestParam String nombre, @RequestParam String correo,
                                   @RequestParam(required = false) String contrasena,
                                   Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userService.findByEmail(username);
        user.setNombre(nombre);
        user.setCorreo(correo);
        if (contrasena != null && !contrasena.isEmpty()) {
            user.setContrasena(contrasena);
        }
        userService.save(user);
        model.addAttribute("mensaje", "Perfil actualizado con éxito.");
        return "perfil";
    }
}

