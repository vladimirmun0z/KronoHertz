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
        
        Reloj reloj = serviceRelojes.buscarPorId(idReloj);
        
        Reserva reserva = new Reserva();
        reserva.setFechaReserva(new Date()); 
        reserva.setEstado("PENDIENTE");      
        reserva.setReloj(reloj);          
        
        serviceReservas.guardar(reserva);
        attributes.addFlashAttribute("msg", "¡Tu solicitud de cotización para el reloj " + reloj.getNomReloj() + " ha sido procesada con éxito!");
        return "redirect:/"; 
    }
}