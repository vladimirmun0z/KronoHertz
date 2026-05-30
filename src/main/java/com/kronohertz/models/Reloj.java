package com.kronohertz.models;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "relojes")
public class Reloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nom_reloj")
    private String nomReloj;
    
    private String descripcion;
    private Date fecha;
    private Double costo;
    private Integer destacado;
    private String imagen;
    private String detalles;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcaReloj marcaReloj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomReloj() {
        return nomReloj;
    }

    public void setNomReloj(String nomReloj) {
        this.nomReloj = nomReloj;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getDestacado() {
        return destacado;
    }

    public void setDestacado(Integer destacado) {
        this.destacado = destacado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public MarcaReloj getMarcaReloj() {
        return marcaReloj;
    }

    public void setMarcaReloj(MarcaReloj marcaReloj) {
        this.marcaReloj = marcaReloj;
    }

    @Override
    public String toString() {
        return "Reloj [id=" + id + ", nomReloj=" + nomReloj + ", costo=" + costo + ", activo=" + activo + ", marcaReloj=" + marcaReloj + "]";
    }
}