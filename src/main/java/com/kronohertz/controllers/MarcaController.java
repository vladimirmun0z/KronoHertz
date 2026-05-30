package com.kronohertz.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kronohertz.models.MarcaReloj;
import com.kronohertz.services.IMarcaService;

@Controller
@RequestMapping(value="/marcas")
public class MarcaController {

    @Autowired
    private IMarcaService marcaService; 

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<MarcaReloj> lista = marcaService.buscarTodo();
        model.addAttribute("marcas", lista);
        return "listaMarca";
    }

    @GetMapping("/create")
    public String crear(Model model) {
        model.addAttribute("marca", new MarcaReloj());
        return "formularioMarca";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute("marca") MarcaReloj marca, RedirectAttributes attributes) {
        marca.setActivo(true); 
        marcaService.guardar(marca);
        attributes.addFlashAttribute("msg", "¡Manufactura procesada con éxito dentro de KronoHertz!");
        return "redirect:/marcas/index";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int id, Model model) {
        marcaService.eliminar(id);
        model.addAttribute("msg", "La casa manufacturera ha sido removida del sistema KronoHertz con éxito.");
        return "mensaje";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        MarcaReloj marca = marcaService.buscarPorId(id);
        model.addAttribute("marca", marca);
        return "formularioMarca";
    }
}