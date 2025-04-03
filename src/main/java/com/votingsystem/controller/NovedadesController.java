package com.votingsystem.controller;

import com.votingsystem.model.Novedad;
import com.votingsystem.service.NovedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/novedades")
public class NovedadesController {

    @Autowired
    private NovedadService novedadService;

    @GetMapping
    public String mostrarNovedades(Model model) {
        model.addAttribute("novedades", novedadService.findAll());
        return "publicar_novedad";
    }

    @PostMapping
    public String publicarNovedad(@ModelAttribute Novedad novedad) {
        novedadService.save(novedad);
        return "redirect:/admin/novedades";
    }

    @PostMapping("/editar")
    public String editarNovedad(@ModelAttribute Novedad novedad) {
        novedadService.update(novedad);
        return "redirect:/admin/novedades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarNovedad(@PathVariable Long id) {
        novedadService.delete(id);
        return "redirect:/admin/novedades";
    }
}

