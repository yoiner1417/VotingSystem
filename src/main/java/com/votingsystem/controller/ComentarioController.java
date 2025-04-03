package com.votingsystem.controller;

import com.votingsystem.model.Comentario;
import com.votingsystem.model.Publicacion;
import com.votingsystem.service.ComentarioService;
import com.votingsystem.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/comentar/{publicacionId}")
    public String mostrarFormularioComentario(@PathVariable Long publicacionId, Model model) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(publicacionId);
        model.addAttribute("publicacion", publicacion);
        model.addAttribute("comentarios", comentarioService.obtenerComentariosPorPublicacion(publicacionId));
        return "comentar";
    }

    @PostMapping("/comentar")
    public String agregarComentario(@RequestParam Long publicacionId,
                                    @RequestParam String contenido,
                                    Authentication authentication) {
        Publicacion publicacion = publicacionService.obtenerPublicacionPorId(publicacionId);
        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setAutor(authentication.getName());
        comentario.setFechaComentario(LocalDateTime.now());
        comentario.setPublicacion(publicacion);
        comentarioService.guardarComentario(comentario);
        return "redirect:/comentar/" + publicacionId;
    }
}

