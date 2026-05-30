package com.kronohertz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column; 

@Entity
@Table(name = "marca_relojes")
public class MarcaReloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nom_marca")
    private String nomMarca;
    private String descripcion;
    private boolean activo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomMarca() {
        return nomMarca;
    }

    public void setNomMarca(String nomMarca) {
        this.nomMarca = nomMarca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "MarcaReloj [id=" + id + ", nomMarca=" + nomMarca + ", descripcion=" + descripcion + ", activo=" + activo + "]";
    }
}