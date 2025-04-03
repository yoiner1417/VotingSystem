package com.votingsystem.controller;

import com.votingsystem.model.Activity;
import com.votingsystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GestionarActividadesController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("/admin/actividades/agregar")
    public String agregarActividad(@ModelAttribute Activity activity, Model model) {
        activityService.save(activity);
        model.addAttribute("mensaje", "Actividad agregada exitosamente");
        return "redirect:/admin/actividades";
    }

    @GetMapping("/admin/actividades/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        model.addAttribute("actividad", activityService.findById(id));
        return "editar_actividad";
    }

    @PostMapping("/admin/actividades/editar")
    public String editarActividad(@ModelAttribute Activity activity, Model model) {
        activityService.save(activity);
        model.addAttribute("mensaje", "Actividad actualizada exitosamente");
        return "redirect:/admin/actividades";
    }

    @GetMapping("/admin/actividades/eliminar/{id}")
    public String eliminarActividad(@PathVariable Long id, Model model) {
        activityService.deleteById(id);
        model.addAttribute("mensaje", "Actividad eliminada exitosamente");
        return "redirect:/admin/actividades";
    }
}

