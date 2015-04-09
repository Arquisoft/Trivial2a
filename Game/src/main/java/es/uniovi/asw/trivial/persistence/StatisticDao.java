package persistence;

import java.util.ArrayList;
import java.util.Map;

public interface StatisticDao {

	/**
	 * M�todo para obtener las estad�sticas sobre deportes
	 * @return Map<RespuestasTotalesDeportes, RespuestasCorrectasDeportes>
	 */
	 ArrayList<Integer> getStatisticSports();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Entretenimiento
	 * @return Map<RespuestasTotalesShows, RespuestasCorrectasShows>
	 */
	 ArrayList<Integer> getStatisticShows();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Ciencia
	 * @return Map<RespuestasTotalesCiencia, RespuestasCorrectasCiencia>
	 */
	 ArrayList<Integer> getStatisticScience();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Arte y Literatura
	 * @return Map<RespuestasTotalesArte, RespuestasCorrectasArte>
	 */
	 ArrayList<Integer> getStatisticArt();
	
	/**
	 * m�todo para obtener las estad�sticas sobre Geograf�a
	 * @return Map<RespuestasTotalesGeografia, RespuestasCorrectasGeografia>
	 */
	 ArrayList<Integer> getStatisticGeography();
	
	/**
	 * M�todo para obtener las estad�sticas sobre Historia
	 * @return Map<RespuestasTotalesHistoria, RespuestasCorrectasHistoria>
	 */
	 ArrayList<Integer> getStatisticHistory();
	
	/**
	 * M�todo para obtener las estad�sticas clasificadas por temas
	 * @return Map<Tema, RespuestasTotalesTema, RespuestasCorrectasTema>
	 */
	Map<String,ArrayList<Integer>> getAllStatistics();
	
	/**
	 * M�todo de utilidad para obtener el �ltimo ID de la tabla Statistics
	 * @return int ID
	 */
	int getLastId();
	
	/**
	 * M�todo para obtener el n�mero total de respuestas
	 * @return
	 */
	int getRespuestasTotales();
	
	/**
	 * M�todo para obtener el n�mero total de respuestas acertadas
	 * @return
	 */
	int getRespuestasCorrectas();
	
	/**
	 * M�todo para obtener el n�mero total de respuestas incorrectas
	 * @return
	 */
	int getRespuestasIncorrectas();
}
