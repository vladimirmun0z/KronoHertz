package com.kronohertz.services;

import java.util.List;
import com.kronohertz.models.MarcaReloj;

public interface IMarcaService {
    List<MarcaReloj> buscarTodo();
    void guardar(MarcaReloj marca);
    MarcaReloj buscarPorId(int id);
    void eliminar(int id);
}