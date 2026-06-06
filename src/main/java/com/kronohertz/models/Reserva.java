package com.kronohertz.models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Date fechaReserva;
    private String estado; 

    // Relación ManyToOne con la entidad Reloj de KronoHertz
    @ManyToOne
    @JoinColumn(name = "id_reloj") 
    private Reloj reloj;

    // Métodos Getter y Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Date getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Reloj getReloj() { return reloj; }
    public void setReloj(Reloj reloj) { this.reloj = reloj; }
}