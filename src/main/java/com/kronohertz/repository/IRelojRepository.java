package com.kronohertz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kronohertz.models.Reloj;

public interface IRelojRepository extends JpaRepository<Reloj, Integer> {
}