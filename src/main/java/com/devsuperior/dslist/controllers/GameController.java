package com.devsuperior.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.services.GameService;

/**
 * Porta de entrada do BackEnd
 * Disponipiliza a API
 * É a interface da API
 * Expõe um endpoint para o mundo externo
 */

/*
 * Configuração do caminho da API
 */
@RestController
@RequestMapping(value = "/games")
public class GameController {

	/**
	 * Injeção de service dentro do controller para comunicação entre camadas
	 */
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<GameMinDTO> findAll(){
		List<GameMinDTO> gameMinDTOs = gameService.findAll();
		return gameMinDTOs;
	}
	
	//@PathVariable associa o id enviado no corpo da requisição ao parãmetro
	//deve conter o mesmo nome {gameId}
	@GetMapping(value = "/{gameId}")
	public GameDTO findById(@PathVariable Long gameId) {
		GameDTO gameDTO = gameService.findById(gameId);
		return gameDTO;
	}
}
