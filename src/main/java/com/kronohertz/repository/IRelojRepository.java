package com.kronohertz.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kronohertz.models.Reloj;

public interface IRelojRepository extends JpaRepository<Reloj, Integer> {

    List<Reloj> findByMarcaRelojId(int idMarca);
}