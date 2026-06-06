package com.kronohertz.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.kronohertz.models.Reloj;
import com.kronohertz.services.IRelojService;
import com.kronohertz.services.IMarcaService; 

@Controller
public class HomeController {

    @Autowired
    private IRelojService relojService;

    @Autowired // CORREGIDO: Inyección de la interfaz para resolver la línea 44
    private IMarcaService marcaService;

    /**
     * Carga el portal de bienvenida público con soporte para filtros por marca.
     */
    @GetMapping("/")
    public String mostrarHome(@RequestParam(value = "genero", required = false) Integer idMarca, Model model) {
        List<Reloj> lista;

        // Si el usuario seleccionó una marca en el buscador, filtramos; si no, cargamos todo
        if (idMarca != null) {
            lista = relojService.buscarPorMarca(idMarca);
            model.addAttribute("marcaSeleccionada", idMarca); // Mantiene el estado seleccionado en el buscador
        } else {
            lista = relojService.buscarTodo();
        }

        model.addAttribute("relojes", lista);
        
        // CARGADO: Ahora marcaService está declarado arriba, por lo que este método funciona perfectamente
        model.addAttribute("listaMarcas", marcaService.buscarTodo());
        
        return "homeKronoHertz";
    }

    /**
     * Carga la pieza de alta relojería detallada usando la URL limpia.
     */
    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable("id") int id, Model model) {
        Reloj relojEncontrado = relojService.buscarPorId(id);
        
        if (relojEncontrado == null) {
            return "redirect:/";
        }
        
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