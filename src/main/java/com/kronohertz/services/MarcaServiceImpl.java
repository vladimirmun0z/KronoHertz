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
    private IMarcasRepository marcasRepo;

    @Override
    public List<MarcaReloj> buscarTodo() {
        return marcasRepo.findAll();
    }

    @Override
    public void guardar(MarcaReloj marca) {
        marcasRepo.save(marca);
    }

    @Override
    public MarcaReloj buscarPorId(int id) {
        Optional<MarcaReloj> optional = marcasRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(int id) {
        marcasRepo.deleteById(id);
    }
}