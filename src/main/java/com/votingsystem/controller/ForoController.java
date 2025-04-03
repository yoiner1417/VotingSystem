package com.votingsystem.controller;

import com.votingsystem.model.Publicacion;
import com.votingsystem.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForoController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/foro")
    public String foro(Model model, @RequestParam(defaultValue = "0") int pagina) {
        Page<Publicacion> publicaciones = publicacionService.obtenerTodasLasPublicaciones(PageRequest.of(pagina, 10));
        model.addAttribute("publicaciones", publicaciones.getContent());
        model.addAttribute("paginaActual", pagina);
        model.addAttribute("totalPaginas", publicaciones.getTotalPages());
        return "foro";
    }
}

