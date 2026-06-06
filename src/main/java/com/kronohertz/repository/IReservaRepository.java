package com.kronohertz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kronohertz.models.Reserva;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Integer> {
    // Debe llamarse IReservaRepository para que haga sinergia con el ServiceImpl
}