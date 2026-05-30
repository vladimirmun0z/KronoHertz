package com.kronohertz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kronohertz.models.Rol;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
}