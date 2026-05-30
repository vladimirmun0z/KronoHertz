package com.kronohertz.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kronohertz.models.Reloj;
import com.kronohertz.repository.IRelojRepository; 

@Service
public class RelojServiceImpl implements IRelojService {

    @Autowired
    private IRelojRepository relojRepo;

    @Override
    public List<Reloj> buscarTodo() {
        return relojRepo.findAll();
    }

    @Override
    public void guardar(Reloj reloj) {
        relojRepo.save(reloj);
    }

    @Override
    public Reloj buscarPorId(int id) {
        Optional<Reloj> optional = relojRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(int id) {
        relojRepo.deleteById(id);
    }
}