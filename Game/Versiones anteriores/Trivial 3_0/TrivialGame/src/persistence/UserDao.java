package persistence;

import java.util.List;

import model.Statistic;
import model.User;

/**
 * Persistencia para User
 * @author maria
 *
 */

public interface UserDao {
	/**
	 * Comprueba si ya existe un usuario con ese login
	 * Es sensible a MAYUS/minus
	 * @return true-valid false-notValid
	 */
	boolean isValidLogin(String login);
	
	/**
	 * Almacena un nuevo usuario (login+statistic) de la aplicación en la BD
	 * @param user (usuario a guardar)
	 */
	void save (User user);
	
	/**
	 * Actualiza un usuario de la BD (actualiza su statistic)
	 * @param user
	 */
	void update(User user);
	
	/**
	 * Actualiza el login de un usuario de la BD
	 * @param user
	 */
	void updateLogin(User user, String nuevoLogin);
	
	/**
	 * Elimina el usuario cuyo Login es pasado por parametro
	 * NOTA:Solo elimina el usuario, sus estadísticas no
	 * @param login
	 */
	void delete(String login);
	
	/**
	 * Obtiene la estadística del usuario pasado por parámetro
	 * @param user
	 * @return Statistic (del user)
	 */
	Statistic getStatistic(User user);
	
	/**
	 * Devuelve una lista con todos los usuarios (login+statistic) de la aplicación
	 * @return lista de usuarios de la aplicacion
	 */
	List<User> getUsers();

	
}
