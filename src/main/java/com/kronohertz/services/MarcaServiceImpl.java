package com.kronohertz.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kronohertz.models.MarcaReloj;
import com.kronohertz.repository.IMarcasRepository; 

@Service
public class MarcaServiceImpl implements IMarcaService {

    @Autowired
    private IMarcasRepository marcaRepo; 

    @Override
    public List<MarcaReloj> buscarTodo() {
        return marcaRepo.findAll();
    }

    @Override
    public void guardar(MarcaReloj marca) {
        marcaRepo.save(marca);
    }

    @Override
    public MarcaReloj buscarPorId(int id) {
        Optional<MarcaReloj> optional = marcaRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void eliminar(int id) {
        marcaRepo.deleteById(id);
    }
}