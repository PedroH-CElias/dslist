package com.devsuperior.dslist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;

/**
 * Camada de comunicação com o Banco de Dados
 * 
 * Seguindo a arquitetura, o service devolve uma entidade
 */
public interface GameRepository extends JpaRepository<Game, Long>{
	
	@Query(nativeQuery = true, value = """
			SELECT tb_game.id, tb_game.title, tb_game.game_year AS gameYear, tb_game.img_url AS imgUrl,
			tb_game.short_description AS shortDescription, tb_belonging.position
			FROM tb_game
			INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
			WHERE tb_belonging.game_list_id = :gameListId
			ORDER BY tb_belonging.position
				""")
	
	List<GameMinProjection> searchByList(Long gameListId);

}
