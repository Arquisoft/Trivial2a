package es.uniovi.asw.trivial.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.trivial.conf.Conf;
import es.uniovi.asw.trivial.conf.Jdbc;
import es.uniovi.asw.trivial.model.Statistic;
import es.uniovi.asw.trivial.model.User;
import es.uniovi.asw.trivial.persistence.UserDao;

public class UserJdbcDao implements UserDao {

	@Override
	public boolean isValidLogin(String login) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("User.isValidLogin");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			rs = ps.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			return false;
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	@Override
	public void save(User user) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String SQL = Conf.get("Statistic.getMaxID");
		int statisticID = 0;
		int rows = 0;
		Statistic s = user.getStatistics();
		try {
			con = Jdbc.getConnection();
			// Buscar �ltimo ID statistic
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next())
				statisticID = rs.getInt(1);
			ps.close();
			// Guardar estad�stica usuario
			ps = con.prepareStatement(Conf.get("Statistic.saveStatistic"));
			ps.setInt(1, statisticID + 1);
			ps.setInt(2, s.getTotalsports());
			ps.setInt(3, s.getSports());
			ps.setInt(4, s.getTotalshowsAndEntertainment());
			ps.setInt(5, s.getShowsAndEntertainment());
			ps.setInt(6, s.getTotalscienceAndTechnology());
			ps.setInt(7, s.getScienceAndTechnology());
			ps.setInt(8, s.getTotalartAndLiterature());
			ps.setInt(9, s.getArtAndLiterature());
			ps.setInt(10, s.getTotalgeography());
			ps.setInt(11, s.getGeography());
			ps.setInt(12, s.getTotalhistory());
			ps.setInt(13, s.getHistory());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("No se pudo almacenar la estad�stica");
			}
			ps.close();
			// Guardamos al usuario
			ps = con.prepareStatement(Conf.get("User.saveUser"));
			ps.setString(1, user.getLogin());
			ps.setInt(2, statisticID + 1);
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("El usuario " + user.getLogin()
						+ " ya existe en la BD");
			} else
				con.commit();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps);
		}

	}

	@Override
	public void update(User user) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String SQL = Conf.get("User.getStatisticID");
		int statisticID = 0;
		int rows = 0;
		Statistic s = user.getStatistics();
		try {
			con = Jdbc.getConnection();
			// Buscar ID statistic del user
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next())
				statisticID = rs.getInt(1);
			else
				throw new SQLException("El usuario no existe");
			ps.close();
			// Actualizar estad�stica usuario
			ps = con.prepareStatement(Conf.get("Statistic.update"));
			ps.setInt(1, s.getTotalsports());
			ps.setInt(2, s.getSports());
			ps.setInt(3, s.getTotalshowsAndEntertainment());
			ps.setInt(4, s.getShowsAndEntertainment());
			ps.setInt(5, s.getTotalscienceAndTechnology());
			ps.setInt(6, s.getScienceAndTechnology());
			ps.setInt(7, s.getTotalartAndLiterature());
			ps.setInt(8, s.getArtAndLiterature());
			ps.setInt(9, s.getTotalgeography());
			ps.setInt(10, s.getGeography());
			ps.setInt(11, s.getTotalhistory());
			ps.setInt(12, s.getHistory());
			ps.setInt(13, statisticID);
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("No se pudo actualizar la estad�stica");
			} else
				con.commit();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps);
		}

	}

	@Override
	public void delete(String login) {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(Conf.get("User.deleteLogin"));

			ps.setString(1, login);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("No se encontr� al usuario " + login);
			} else
				con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(ps);
		}

	}

	@Override
	public Statistic getStatistic(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		Statistic statistic = new Statistic();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(Conf.get("User.getStatisticID"));
			ps.setString(1, user.getLogin());
			rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("STATISTICID");
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = con.prepareStatement(Conf.get("Statistic.getByID"));
				ps2.setInt(1, ID);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					statistic.setTotalsports(rs2.getInt("totalsports"));
					statistic.setSports(rs2.getInt("correctsports"));
					statistic.setTotalshowsAndEntertainment(rs2
							.getInt("totalshows"));
					statistic.setShowsAndEntertainment(rs2
							.getInt("correctshows"));
					statistic.setTotalscienceAndTechnology(rs2
							.getInt("totalscience"));
					statistic.setScienceAndTechnology(rs2
							.getInt("correctscience"));
					statistic.setTotalartAndLiterature(rs2.getInt("totalart"));
					statistic.setArtAndLiterature(rs2.getInt("correctart"));
					statistic.setTotalgeography(rs2.getInt("totalgeography"));
					statistic.setGeography(rs2.getInt("correctgeography"));
					statistic.setTotalhistory(rs2.getInt("totalhistory"));
					statistic.setHistory(rs2.getInt("correcthistory"));
					user.setStatistics(statistic);
				}
				rs2.close();
				ps2.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps);
		}

		return statistic;
	}

	@Override
	public List<User> getUsers() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<User> users = new ArrayList<User>();

		try {
			con = Jdbc.getConnection();
			// FIXME: Cambiar por consulta externalizada cuando funcione el Conf
			ps = con.prepareStatement("select * from users");
			rs = ps.executeQuery();
			
			
			while (rs.next()) {
				System.out.println(rs.getString("LOGIN"));
				User user = new User(rs.getString("LOGIN"));
				int ID = rs.getInt("STATISTICID");
				System.out.println(ID);
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				// FIXME: Cambiar por consulta externalizada cuando funcione el
				// Conf
				ps2 = con
						.prepareStatement("select * from statistics where statisticid = ?");
				ps2.setInt(1, ID);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					Statistic statistic = new Statistic();
					statistic.setTotalsports(rs2.getInt("totalsports"));
					statistic.setSports(rs2.getInt("correctsports"));
					statistic.setTotalshowsAndEntertainment(rs2
							.getInt("totalshows"));
					statistic.setShowsAndEntertainment(rs2
							.getInt("correctshows"));
					statistic.setTotalscienceAndTechnology(rs2
							.getInt("totalscience"));
					statistic.setScienceAndTechnology(rs2
							.getInt("correctscience"));
					statistic.setTotalartAndLiterature(rs2.getInt("totalart"));
					statistic.setArtAndLiterature(rs2.getInt("correctart"));
					statistic.setTotalgeography(rs2.getInt("totalgeography"));
					statistic.setGeography(rs2.getInt("correctgeography"));
					statistic.setTotalhistory(rs2.getInt("totalhistory"));
					statistic.setHistory(rs2.getInt("correcthistory"));
					user.setStatistics(statistic);
				}
				rs2.close();
				ps2.close();

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps);
		}

		return users;
	}

	@Override
	public void updateLogin(User user, String nuevoLogin) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String SQL = Conf.get("User.updateLogin");
		int rows = 0;
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			ps.setString(1, nuevoLogin);
			ps.setString(2, user.getLogin());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("No se pudo actualizar el usuario");
			} else {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps);
		}
	}

}
