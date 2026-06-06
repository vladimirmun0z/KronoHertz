package com.kronohertz.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kronohertz.models.Reloj;
import com.kronohertz.models.Reserva;
import com.kronohertz.services.IRelojService; // Tu servicio de control de relojes
import com.kronohertz.services.IReservaService;

@Controller
public class ReservaController {

    @Autowired
    private IReservaService serviceReservas;

    @Autowired
    private IRelojService serviceRelojes; 

    @PostMapping("/reservas/save")
    public String realizarReserva(@RequestParam("idReloj") int idReloj, RedirectAttributes attributes) {
        
        // 1. Buscamos el objeto de alta relojería por su ID
        Reloj reloj = serviceRelojes.buscarPorId(idReloj);
        
        // 2. Instanciamos la nueva Reserva
        Reserva reserva = new Reserva();
        reserva.setFechaReserva(new Date()); 
        reserva.setEstado("PENDIENTE");      
        reserva.setReloj(reloj);             // Inyección de la relación ManyToOne
        
        // 3. Persistimos en la base de datos de KronoHertz
        serviceReservas.guardar(reserva);
        
        // 4. Notificación para la cartelera Gamma
        attributes.addFlashAttribute("msg", "¡Tu solicitud de cotización para el reloj " + reloj.getNomReloj() + " ha sido procesada con éxito!");
        
        // Redirecciona al catálogo principal de piezas
        return "redirect:/"; 
    }
}