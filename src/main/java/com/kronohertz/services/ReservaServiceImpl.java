package com.kronohertz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.kronohertz.models.Reserva;
import com.kronohertz.repository.IReservaRepository;

@Service // <-- ¡ESTA ANOTACIÓN ES LA QUE FALTA!
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private IReservaRepository repoReservas;

    @Override
    public void guardar(Reserva reserva) {
        repoReservas.save(reserva);
    }
}