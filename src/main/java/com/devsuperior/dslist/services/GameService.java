package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;

/**
 * Camada de serviços, de regras de negócio.
 * Seguindo a arquitetura, o service devolve um DTO
 */
@Service
public class GameService {

	/**
	 * Injeção de repository dentro do service para comunicação entre camadas
	 */
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game> games = gameRepository.findAll();
		
		//Pra cada Game, ele cria um novo GameMinDTO e adiciona em uma lista
		List<GameMinDTO> gameMinDTOs = games //
				.stream() 
				.map(game -> new GameMinDTO(game)) //
				.toList();
		
		return gameMinDTOs;
	}
	
	//Declara que apenas usará o método para leitura e não tera escrita no BD
	@Transactional(readOnly = true)
	public GameDTO findById(Long gameId) {
		Game game = gameRepository //
				.findById(gameId) //
				.get(); // .get() para pegar o objeto game e não Optional<Game>
		
		GameDTO gameDTO = new GameDTO(game);
		return gameDTO;
	}
	
}
