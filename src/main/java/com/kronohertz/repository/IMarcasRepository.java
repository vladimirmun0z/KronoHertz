package com.kronohertz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kronohertz.models.MarcaReloj;

@Repository
public interface IMarcasRepository extends JpaRepository<MarcaReloj, Integer> {
}