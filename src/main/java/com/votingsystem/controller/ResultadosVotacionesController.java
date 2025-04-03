package com.votingsystem.controller;

import com.votingsystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResultadosVotacionesController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/resultados-votaciones")
    public String mostrarResultadosVotaciones(Model model, @RequestParam(required = false) String mensaje) {
        model.addAttribute("resultadosCandidatos", voteService.obtenerResultadosCandidatos());
        model.addAttribute("resultadosActividades", voteService.obtenerResultadosActividades());

        if (mensaje != null) {
            if (mensaje.equals("ya_votaste")) {
                model.addAttribute("mensaje", "Ya has emitido tu voto anteriormente.");
            } else if (mensaje.equals("voto_exitoso")) {
                model.addAttribute("mensaje", "Tu voto ha sido registrado con éxito.");
            }
        }

        return "resultados_votaciones";
    }
}

