package com.votingsystem.controller;

import com.votingsystem.model.User;
import com.votingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminRegistroController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/registro")
    public String mostrarFormularioRegistro() {
        return "registrar_admin";
    }

    @PostMapping("/admin/registro")
    public String registrarAdmin(@RequestParam String nombre, @RequestParam String correo,
                                 @RequestParam String contrasena, Model model) {
        User newAdmin = new User();
        newAdmin.setNombre(nombre);
        newAdmin.setCorreo(correo);
        newAdmin.setContrasena(contrasena);
        newAdmin.setRol("administrador");

        userService.save(newAdmin);
        model.addAttribute("mensaje", "Administrador registrado con éxito");
        return "registrar_admin";
    }
}

