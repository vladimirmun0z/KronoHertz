package com.kronohertz.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kronohertz.models.Reloj;
import com.kronohertz.models.MarcaReloj; 
import com.kronohertz.services.IRelojService;
import com.kronohertz.services.IMarcaService; 

@Controller
@RequestMapping("/relojes")
public class RelojController {

    @Autowired
    private IRelojService relojService;

    @Autowired
    private IMarcaService marcaService; 

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Reloj> lista = relojService.buscarTodo(); 
        model.addAttribute("relojes", lista); 
        return "tablaReloj"; 
    }

    @GetMapping("/create")
    public String crear(Model model) { 
        Reloj nuevoReloj = new Reloj();
        nuevoReloj.setFecha(new Date()); 
        model.addAttribute("reloj", nuevoReloj);
        
        List<MarcaReloj> listaMarcas = marcaService.buscarTodo();
        model.addAttribute("marcas", listaMarcas); 
        
        return "formularioReloj";
    }
    
    @PostMapping("/save")
    public String guardar(Reloj reloj, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes attributes) {
        
        if (!multiPart.isEmpty()) {
            String rutaDestino = "src/main/resources/static/images/";
            try {
                String nombreImagen = multiPart.getOriginalFilename();
                nombreImagen = nombreImagen.replace(" ", "-");
                
                byte[] bytes = multiPart.getBytes();
                Path path = Paths.get(rutaDestino + nombreImagen);
                Files.write(path, bytes);
                
                reloj.setImagen(nombreImagen);
            } catch (IOException e) {
                System.out.println("Error al guardar archivo: " + e.getMessage());
            }
        } else if (reloj.getImagen() == null || reloj.getImagen().isEmpty()) {
            reloj.setImagen("no-image.png");
        }
        
        relojService.guardar(reloj);
        attributes.addFlashAttribute("msg", "¡Pieza de alta relojería guardada con éxito!");
        return "redirect:/relojes/index";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int id, Model model) {
        Reloj reloj = relojService.buscarPorId(id);
        model.addAttribute("reloj", reloj);
        return "detalleReloj";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int id, Model model) {
        relojService.eliminar(id); 
        model.addAttribute("msg", "La pieza de alta relojería ha sido removida del inventario de KronoHertz con éxito.");
        return "mensaje";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}