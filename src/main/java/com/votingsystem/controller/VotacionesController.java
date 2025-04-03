package com.votingsystem.controller;

import com.votingsystem.model.Candidate;
import com.votingsystem.model.Activity;
import com.votingsystem.model.User;
import com.votingsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controllers
public class VotacionesController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    private AuditService auditService;

    @GetMapping("/votaciones")
    public String mostrarVotaciones(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userService.findByEmail(username);

            if (user == null) {
                loggingService.logWarning("Intento de acceso con usuario no encontrado: " + username);
                auditService.logAction("Unknown", "Intento de acceso con usuario no encontrado");
                return "redirect:/login";
            }

            if (voteService.hasUserVoted(user.getId())) {
                loggingService.logInfo("Usuario " + username + " intentó votar nuevamente");
                auditService.logAction(username, "Intento de voto múltiple");
                return "redirect:/resultados-votaciones?mensaje=ya_votaste";
            }

            if (!voteService.estanVotacionesAbiertas()) {
                loggingService.logInfo("Intento de acceso a votaciones cerradas por " + username);
                auditService.logAction(username, "Intento de acceso a votaciones cerradas");
                model.addAttribute("mensaje", "Las votaciones están cerradas actualmente.");
                return "votaciones_cerradas";
            }

            List<Candidate> candidatos = candidateService.findAll();
            List<Activity> actividades = activityService.findAll();

            if (candidatos.isEmpty() || actividades.isEmpty()) {
                loggingService.logWarning("No hay candidatos o actividades disponibles para votar");
                model.addAttribute("mensaje", "No hay opciones disponibles para votar en este momento.");
                return "votaciones_no_disponibles";
            }

            model.addAttribute("candidatos", candidatos);
            model.addAttribute("actividades", actividades);
            return "votaciones";
        } else {
            loggingService.logWarning("Intento de acceso no autenticado a las votaciones");
            auditService.logAction("Anonymous", "Intento de acceso no autenticado a las votaciones");
            return "redirect:/login";
        }
    }

    @PostMapping("/votar")
    public String votar(@RequestParam Long candidatoId, @RequestParam Long actividadId, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userService.findByEmail(username);

            if (user == null) {
                loggingService.logWarning("Intento de voto con usuario no encontrado: " + username);
                auditService.logAction("Unknown", "Intento de voto con usuario no encontrado");
                return "redirect:/login";
            }

            if (!voteService.estanVotacionesAbiertas()) {
                loggingService.logWarning("Intento de voto con votaciones cerradas por " + username);
                auditService.logAction(username, "Intento de voto con votaciones cerradas");
                return "redirect:/votaciones?mensaje=votaciones_cerradas";
            }

            if (voteService.hasUserVoted(user.getId())) {
                loggingService.logWarning("Intento de voto múltiple por " + username);
                auditService.logAction(username, "Intento de voto múltiple");
                return "redirect:/resultados-votaciones?mensaje=ya_votaste";
            }

            Candidate candidate = candidateService.findById(candidatoId);
            Activity activity = activityService.findById(actividadId);

            if (candidate == null || activity == null) {
                loggingService.logError("Intento de voto con candidato o actividad inválidos por " + username);
                auditService.logAction(username, "Intento de voto con opciones inválidas");
                return "redirect:/votaciones?mensaje=opciones_invalidas";
            }

            try {
                voteService.registerVote(user.getId(), candidatoId, actividadId);
                loggingService.logInfo("Voto registrado exitosamente para el usuario " + username);
                auditService.logAction(username, "Voto registrado exitosamente");
                return "redirect:/resultados-votaciones?mensaje=voto_exitoso";
            } catch (IllegalStateException e) {
                loggingService.logError("Error al registrar voto para el usuario " + username);
                auditService.logAction(username, "Error al registrar voto");
                return "redirect:/votaciones?mensaje=error_al_votar";
            }
        } else {
            loggingService.logWarning("Intento de voto no autenticado");
            auditService.logAction("Anonymous", "Intento de voto no autenticado");
            return "redirect:/login";
        }
    }
}

