package com.votingsystem.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exc, Model model) {
        model.addAttribute("mensaje", "El archivo es demasiado grande. El tamaño máximo permitido es de 10MB.");
        return "error";
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(IOException exc, Model model) {
        model.addAttribute("mensaje", "Error al procesar el archivo: " + exc.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception exc, Model model) {
        model.addAttribute("mensaje", "Ha ocurrido un error: " + exc.getMessage());
        return "error";
    }
}

