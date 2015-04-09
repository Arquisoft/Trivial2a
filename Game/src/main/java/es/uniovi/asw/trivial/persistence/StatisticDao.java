package persistence;

import java.util.ArrayList;
import java.util.Map;

import model.User;

public interface StatisticDao {

	/**
	 * Método para obtener las estadísticas sobre deportes
	 * @return <RespuestasTotalesDeportes, RespuestasCorrectasDeportes>
	 */
	 ArrayList<Integer> getStatisticSports();
	
	/**
	 * Método para obtener las estadísticas sobre Entretenimiento
	 * @return <RespuestasTotalesShows, RespuestasCorrectasShows>
	 */
	 ArrayList<Integer> getStatisticShows();
	
	/**
	 * Método para obtener las estadísticas sobre Ciencia
	 * @return <RespuestasTotalesCiencia, RespuestasCorrectasCiencia>
	 */
	 ArrayList<Integer> getStatisticScience();
	
	/**
	 * Método para obtener las estadísticas sobre Arte y Literatura
	 * @return <RespuestasTotalesArte, RespuestasCorrectasArte>
	 */
	 ArrayList<Integer> getStatisticArt();
	
	/**
	 * método para obtener las estadísticas sobre Geografía
	 * @return <RespuestasTotalesGeografia, RespuestasCorrectasGeografia>
	 */
	 ArrayList<Integer> getStatisticGeography();
	
	/**
	 * Método para obtener las estadísticas sobre Historia
	 * @return <RespuestasTotalesHistoria, RespuestasCorrectasHistoria>
	 */
	 ArrayList<Integer> getStatisticHistory();
	
	/**
	 * Método para obtener las estadísticas clasificadas por temas
	 * @return <Tema, RespuestasTotalesTema, RespuestasCorrectasTema>
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
	
	/**
	 * Método para obtener la estadísitca del usuario 
	 * @param user
	 * @return <Tema,RespuestasCorrectas, RespuestasTotales>
	 */
	Map<String,Integer[]> getStatisticUser(User user);
}
