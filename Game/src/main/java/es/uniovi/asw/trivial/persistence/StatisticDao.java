package persistence;

import java.util.ArrayList;
import java.util.Map;

public interface StatisticDao {

	/**
	 * Método para obtener las estadísticas sobre deportes
	 * @return Map<RespuestasTotalesDeportes, RespuestasCorrectasDeportes>
	 */
	 ArrayList<Integer> getStatisticSports();
	
	/**
	 * Método para obtener las estadísticas sobre Entretenimiento
	 * @return Map<RespuestasTotalesShows, RespuestasCorrectasShows>
	 */
	 ArrayList<Integer> getStatisticShows();
	
	/**
	 * Método para obtener las estadísticas sobre Ciencia
	 * @return Map<RespuestasTotalesCiencia, RespuestasCorrectasCiencia>
	 */
	 ArrayList<Integer> getStatisticScience();
	
	/**
	 * Método para obtener las estadísticas sobre Arte y Literatura
	 * @return Map<RespuestasTotalesArte, RespuestasCorrectasArte>
	 */
	 ArrayList<Integer> getStatisticArt();
	
	/**
	 * método para obtener las estadísticas sobre Geografía
	 * @return Map<RespuestasTotalesGeografia, RespuestasCorrectasGeografia>
	 */
	 ArrayList<Integer> getStatisticGeography();
	
	/**
	 * Método para obtener las estadísticas sobre Historia
	 * @return Map<RespuestasTotalesHistoria, RespuestasCorrectasHistoria>
	 */
	 ArrayList<Integer> getStatisticHistory();
	
	/**
	 * Método para obtener las estadísticas clasificadas por temas
	 * @return Map<Tema, RespuestasTotalesTema, RespuestasCorrectasTema>
	 */
	Map<String,ArrayList<Integer>> getAllStatistics();
	
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
