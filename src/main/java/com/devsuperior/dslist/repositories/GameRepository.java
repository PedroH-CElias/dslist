package com.devsuperior.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dslist.entities.Game;

/**
 * Camada de comunicação com o Banco de Dados
 * 
 * Seguindo a arquitetura, o service devolve uma entidade
 */
public interface GameRepository extends JpaRepository<Game, Long>{

}
