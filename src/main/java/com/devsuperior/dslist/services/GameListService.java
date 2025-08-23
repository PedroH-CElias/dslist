package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll(){
		List<GameList> gameLists = gameListRepository.findAll();
		
		List<GameListDTO> gameListDTOs =  gameLists //
				.stream() //
				.map(gameList -> new GameListDTO(gameList)) //
				.toList();
		
		return gameListDTOs;
	}
	
	@Transactional
	public void move(Long gameListId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> gameList = gameRepository.searchByList(gameListId);
		
		GameMinProjection movedGame =  gameList.remove(sourceIndex);
		gameList.add(destinationIndex, movedGame);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex: sourceIndex;
		/**
		 * Manipula as posições da lista para salvar no Banco a partir das posições origem e destino
		 * Realocando de acordo com as posições minimas e máximas
		 */
		for (int i = min; i <= max; i++) {
			Long gameId = gameList.get(i).getId();
			gameListRepository.updateBelongingPosition(gameListId, gameId, i);
		}
	}

}
