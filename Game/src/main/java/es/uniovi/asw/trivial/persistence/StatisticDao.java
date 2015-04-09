package persistence;

import java.util.Map;

public interface StatisticDao {

	/**
	 * Método para obtener las estadísticas sobre deportes
	 * @return Map<RespuestasTotalesDeportes, RespuestasCorrectasDeportes>
	 */
	Map<Integer, Integer> getStatisticSports();
	
	/**
	 * Método para obtener las estadísticas sobre Entretenimiento
	 * @return Map<RespuestasTotalesShows, RespuestasCorrectasShows>
	 */
	Map<Integer, Integer> getStatisticShows();
	
	/**
	 * Método para obtener las estadísticas sobre Ciencia
	 * @return Map<RespuestasTotalesCiencia, RespuestasCorrectasCiencia>
	 */
	Map<Integer, Integer> getStatisticScience();
	
	/**
	 * Método para obtener las estadísticas sobre Arte y Literatura
	 * @return Map<RespuestasTotalesArte, RespuestasCorrectasArte>
	 */
	Map<Integer, Integer> getStatisticArt();
	
	/**
	 * método para obtener las estadísticas sobre Geografía
	 * @return Map<RespuestasTotalesGeografia, RespuestasCorrectasGeografia>
	 */
	Map<Integer, Integer> getStatisticGeography();
	
	/**
	 * Método para obtener las estadísticas sobre Historia
	 * @return Map<RespuestasTotalesHistoria, RespuestasCorrectasHistoria>
	 */
	Map<Integer, Integer> getStatisticHistory();
	
	/**
	 * Método para obtener las estadísticas clasificadas por temas
	 * @return Map<Tema, RespuestasTotalesTema, RespuestasCorrectasTema>
	 */
	Map<String,Map<Integer,Integer>> getAllStatistics();
	
	/**
	 * Método de utilidad para obtener el último ID de la tabla Statistics
	 * @return int ID
	 */
	int getLastId();
	
	/**
	 * Método para obtener el número total de respuestas
	 * @return
	 */
	int getRespuestasTotales();
	
	/**
	 * Método para obtener el número total de respuestas acertadas
	 * @return
	 */
	int getRespuestasCorrectas();
	
	/**
	 * Método para obtener el número total de respuestas incorrectas
	 * @return
	 */
	int getRespuestasIncorrectas();
}
