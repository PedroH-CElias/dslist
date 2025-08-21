package com.devsuperior.dslist.projections;

/**
 * Consulta customizada
 * Um get pra cada item que deseja buscar no select do repository
 */
public interface GameMinProjection {
	
	Long getId();
	String getTitle();
	Integer getGameYear();
	String getImgUrl();
	String getShortDescription();
	Integer getPosition();
}
