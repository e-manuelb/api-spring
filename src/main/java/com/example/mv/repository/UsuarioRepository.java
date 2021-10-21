package com.example.mv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mv.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}
