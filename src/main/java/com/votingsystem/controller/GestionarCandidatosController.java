package com.votingsystem.controller;

import com.votingsystem.model.Candidate;
import com.votingsystem.service.CandidateService;
import com.votingsystem.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/gestionar-candidatos") // Nueva URL para evitar conflicto
public class GestionarCandidatosController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public String gestionarCandidatos(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Candidate> candidatePage = candidateService.findAllPaginated(PageRequest.of(page, 10));
        model.addAttribute("candidatos", candidatePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", candidatePage.getTotalPages());
        return "gestionar_candidatos";
    }

    @PostMapping("/agregar")
    public String agregarCandidato(@ModelAttribute Candidate candidate, @RequestParam("foto") MultipartFile foto, Model model) {
        try {
            if (!foto.isEmpty()) {
                String fileName = fileStorageService.storeFile(foto);
                candidate.setFoto(fileName);
            }
            candidateService.save(candidate);
            model.addAttribute("mensaje", "Candidato agregado exitosamente");
        } catch (IOException e) {
            model.addAttribute("mensaje", "Error al subir la foto: " + e.getMessage());
        }
        return "redirect:/admin/gestionar-candidatos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        model.addAttribute("candidato", candidateService.findById(id));
        return "editar_candidato";
    }

    @PostMapping("/editar")
    public String editarCandidato(@ModelAttribute Candidate candidate, @RequestParam("foto") MultipartFile foto, Model model) {
        try {
            if (!foto.isEmpty()) {
                String fileName = fileStorageService.storeFile(foto);
                if (candidate.getFoto() != null) {
                    fileStorageService.deleteFile(candidate.getFoto());
                }
                candidate.setFoto(fileName);
            }
            candidateService.save(candidate);
            model.addAttribute("mensaje", "Candidato actualizado exitosamente");
        } catch (IOException e) {
            model.addAttribute("mensaje", "Error al actualizar la foto: " + e.getMessage());
        }
        return "redirect:/admin/gestionar-candidatos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCandidato(@PathVariable Long id, Model model) {
        Candidate candidate = candidateService.findById(id);
        if (candidate != null && candidate.getFoto() != null) {
            try {
                fileStorageService.deleteFile(candidate.getFoto());
            } catch (IOException e) {
                model.addAttribute("mensaje", "Error al eliminar la foto: " + e.getMessage());
            }
        }
        candidateService.deleteById(id);
        model.addAttribute("mensaje", "Candidato eliminado exitosamente");
        return "redirect:/admin/gestionar-candidatos";
    }
}
