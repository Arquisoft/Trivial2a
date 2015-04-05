package es.uniovi.asw.trivial.persistence;

import java.util.Map;

public interface StatisticDao {

	/**
	 * M�todo para obtener las estad�sticas sobre deportes
	 * @return Map<RespuestasTotalesDeportes, RespuestasCorrectasDeportes>
	 */
	Map<Integer, Integer> getStatisticSports();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Entretenimiento
	 * @return Map<RespuestasTotalesShows, RespuestasCorrectasShows>
	 */
	Map<Integer, Integer> getStatisticShows();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Ciencia
	 * @return Map<RespuestasTotalesCiencia, RespuestasCorrectasCiencia>
	 */
	Map<Integer, Integer> getStatisticScience();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Arte y Literatura
	 * @return Map<RespuestasTotalesArte, RespuestasCorrectasArte>
	 */
	Map<Integer, Integer> getStatisticArt();
	
	/**
	 * m�todo para obtener las estad�sticas sobre Geograf�a
	 * @return Map<RespuestasTotalesGeografia, RespuestasCorrectasGeografia>
	 */
	Map<Integer, Integer> getStatisticGeography();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Historia
	 * @return Map<RespuestasTotalesHistoria, RespuestasCorrectasHistoria>
	 */
	Map<Integer, Integer> getStatisticHistory();
	
	/**
	 * M�todo para obtener las estad�sticas clasificadas por temas
	 * @return Map<Tema, RespuestasTotalesTema, RespuestasCorrectasTema>
	 */
	Map<String,Map<Integer,Integer>> getAllStatistics();
	
	/**
	 * M�todo de utilidad para obtener el �ltimo ID de la tabla Statistics
	 * @return int ID
	 */
	int getLastId();
}
