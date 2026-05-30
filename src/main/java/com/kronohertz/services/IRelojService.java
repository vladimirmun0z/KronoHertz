package com.kronohertz.services;

import java.util.List;
import com.kronohertz.models.Reloj;

public interface IRelojService {
    List<Reloj> buscarTodo();
    void guardar(Reloj reloj);
    Reloj buscarPorId(int id);
    void eliminar(int id);
}