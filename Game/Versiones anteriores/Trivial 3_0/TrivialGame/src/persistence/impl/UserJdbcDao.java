package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conf.Conf;
import conf.Jdbc;
import model.Statistic;
import model.User;
import persistence.UserDao;

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
			// Buscar último ID statistic
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next())
				statisticID = rs.getInt(1);
			ps.close();
			// Guardar estadística usuario
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
				throw new SQLException("No se pudo almacenar la estadística");
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
			else throw new SQLException("El usuario no existe");
			ps.close();
			// Actualizar estadística usuario
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
				throw new SQLException("No se pudo actualizar la estadística");
			}
			else con.commit();
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
				throw new SQLException("No se encontró al usuario "+login);
			}
			else con.commit();

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
					statistic.setTotalsports(rs.getInt("totalsports"));
					statistic.setSports(rs.getInt("correctsports"));
					statistic.setTotalshowsAndEntertainment(rs
							.getInt("totalshows"));
					statistic.setShowsAndEntertainment(rs
							.getInt("correctshows"));
					statistic.setTotalscienceAndTechnology(rs
							.getInt("totalscience"));
					statistic.setScienceAndTechnology(rs
							.getInt("correctscience"));
					statistic.setTotalartAndLiterature(rs.getInt("totalart"));
					statistic.setArtAndLiterature(rs.getInt("correctart"));
					statistic.setTotalgeography(rs.getInt("totalgeography"));
					statistic.setGeography(rs.getInt("correctgeography"));
					statistic.setTotalhistory(rs.getInt("totalhistory"));
					statistic.setHistory(rs.getInt("correcthistory"));
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
			ps = con.prepareStatement(Conf.get("User.getUsers"));
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString("LOGIN"));
				int ID = rs.getInt("STATISTICID");
				PreparedStatement ps2 = null;
				ResultSet rs2 = null;
				ps2 = con.prepareStatement(Conf.get("Statistic.getByID"));
				ps2.setInt(1, ID);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					Statistic statistic = new Statistic();
					statistic.setTotalsports(rs.getInt("totalsports"));
					statistic.setSports(rs.getInt("correctsports"));
					statistic.setTotalshowsAndEntertainment(rs
							.getInt("totalshows"));
					statistic.setShowsAndEntertainment(rs
							.getInt("correctshows"));
					statistic.setTotalscienceAndTechnology(rs
							.getInt("totalscience"));
					statistic.setScienceAndTechnology(rs
							.getInt("correctscience"));
					statistic.setTotalartAndLiterature(rs.getInt("totalart"));
					statistic.setArtAndLiterature(rs.getInt("correctart"));
					statistic.setTotalgeography(rs.getInt("totalgeography"));
					statistic.setGeography(rs.getInt("correctgeography"));
					statistic.setTotalhistory(rs.getInt("totalhistory"));
					statistic.setHistory(rs.getInt("correcthistory"));
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
			}
			else con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps);
		}
	}

}
