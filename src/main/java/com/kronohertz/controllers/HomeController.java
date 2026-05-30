package com.kronohertz.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kronohertz.models.Reloj;
import com.kronohertz.services.IRelojService;

@Controller
public class HomeController {

    @Autowired
    private IRelojService relojService;

    @GetMapping("/")
    public String mostrarHome(Model model) {
        List<Reloj> lista = relojService.buscarTodo();
        model.addAttribute("relojes", lista);
        return "homeKronoHertz";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable("id") int id, Model model) {
        Reloj relojEncontrado = relojService.buscarPorId(id);
        model.addAttribute("reloj", relojEncontrado);
        return "detalleReloj"; 
    }

    @GetMapping("/formularioMarca")
    public String mostrarFormularioMarca() {
        return "formularioMarca";
    }

    @GetMapping("/listaMarca")
    public String mostrarListadoMarcas() {
        return "listaMarca";
    }

    @GetMapping("/mensaje")
    public String mostrarMensaje() {
        return "mensaje";
    }
}