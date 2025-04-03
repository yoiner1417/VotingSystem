package com.votingsystem.controller;

import com.votingsystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultadosGraficosController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/resultados-graficos")
    public String mostrarResultadosGraficos(Model model) {
        model.addAttribute("resultadosCandidatos", voteService.obtenerResultadosCandidatos());
        model.addAttribute("resultadosActividades", voteService.obtenerResultadosActividades());
        return "resultados_graficos";
    }
}

