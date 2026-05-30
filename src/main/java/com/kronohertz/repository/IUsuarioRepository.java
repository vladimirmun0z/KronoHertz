package com.kronohertz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kronohertz.models.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}