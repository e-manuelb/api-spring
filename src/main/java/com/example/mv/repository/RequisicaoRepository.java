package com.example.mv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mv.model.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

}
