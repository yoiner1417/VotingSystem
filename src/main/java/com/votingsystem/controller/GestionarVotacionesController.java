package com.votingsystem.controller;

import com.votingsystem.service.EstadisticasService;
import com.votingsystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/votaciones")
public class GestionarVotacionesController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private EstadisticasService estadisticasService;

    @GetMapping
    public String gestionarVotaciones(Model model) {
        model.addAttribute("estadoActual", voteService.getEstadoVotaciones());
        model.addAttribute("totalVotos", estadisticasService.getTotalVotos());
        model.addAttribute("totalUsuarios", estadisticasService.getTotalUsuariosRegistrados());
        model.addAttribute("porcentajeParticipacion", estadisticasService.getPorcentajeParticipacion());
        return "gestionar_votaciones";
    }

    @PostMapping("/estado")
    public String actualizarEstadoVotaciones(@RequestParam String estado, Model model) {
        voteService.setEstadoVotaciones(estado);
        model.addAttribute("mensaje", "Estado de las votaciones actualizado a: " + estado);
        return "redirect:/admin/votaciones";
    }
}

