package es.uniovi.asw.trivial.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.uniovi.asw.trivial.model.User;
import es.uniovi.asw.trivial.conf.Conf;
import es.uniovi.asw.trivial.conf.Jdbc;
import es.uniovi.asw.trivial.persistence.StatisticDao;

public class StatisticJdbcDao implements StatisticDao {

	@Override
	public ArrayList<Integer> getStatisticSports() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getSports");

		ArrayList<Integer> sports = new ArrayList<Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next()) {
				sports.add(0, rs.getInt(1));
				sports.add(1, rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return sports;
	}

	@Override
	public ArrayList<Integer> getStatisticShows() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getShows");

		ArrayList<Integer> shows = new ArrayList<Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				shows.add(0, rs.getInt(1));
				shows.add(1, rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return shows;
	}

	@Override
	public ArrayList<Integer> getStatisticScience() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getScience");

		ArrayList<Integer> science = new ArrayList<Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				science.add(0, rs.getInt(1));
				science.add(1, rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return science;
	}

	@Override
	public ArrayList<Integer> getStatisticArt() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getArt");

		ArrayList<Integer> art = new ArrayList<Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next()) {
				art.add(0, rs.getInt(1));
				art.add(1, rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return art;
	}

	@Override
	public ArrayList<Integer> getStatisticGeography() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getGeography");

		ArrayList<Integer> geo = new ArrayList<Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				geo.add(0, rs.getInt(1));
				geo.add(1, rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return geo;
	}

	@Override
	public ArrayList<Integer> getStatisticHistory() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getHistory");

		ArrayList<Integer> history = new ArrayList<Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next()) {
				history.add(0, rs.getInt(1));
				history.add(1, rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return history;
	}

	@Override
	public Map<String, ArrayList<Integer>> getAllStatistics() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getAll");

		Map<String, ArrayList<Integer>> statistic = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> sports = new ArrayList<Integer>();
		ArrayList<Integer> shows = new ArrayList<Integer>();
		ArrayList<Integer> science = new ArrayList<Integer>();
		ArrayList<Integer> art = new ArrayList<Integer>();
		ArrayList<Integer> geo = new ArrayList<Integer>();
		ArrayList<Integer> history = new ArrayList<Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next()) {
				sports.add(0, rs.getInt(1));
				sports.add(1, rs.getInt(2));
				shows.add(0, rs.getInt(3));
				shows.add(1, rs.getInt(4));
				science.add(0, rs.getInt(5));
				science.add(1, rs.getInt(6));
				art.add(0, rs.getInt(7));
				art.add(1, rs.getInt(8));
				geo.add(0, rs.getInt(9));
				geo.add(1, rs.getInt(10));
				history.add(0, rs.getInt(11));
				history.add(1, rs.getInt(12));
				statistic.put("sports", sports);
				statistic.put("shows", shows);
				statistic.put("science", science);
				statistic.put("art", art);
				statistic.put("geography", geo);
				statistic.put("history", history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, ps);
		}
		return statistic;
	}

	@Override
	public int getLastId() {
		int result = -1;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection c = null;
		String SQL = Conf.get("Statistic.getMaxID");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			if (rs.next())
				result = rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return result;
	}

	@Override
	public int getRespuestasTotales() {
		int result = -1;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection c = null;
		String SQL = Conf.get("Statistic.totalAnswers");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			if (rs.next())
				result = rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return result;
	}

	@Override
	public int getRespuestasCorrectas() {
		int result = -1;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection c = null;
		String SQL = Conf.get("Statistic.totalCorrect");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			if (rs.next())
				result = rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return result;
	}

	@Override
	public int getRespuestasIncorrectas() {
		int result = -1;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection c = null;
		String SQL = Conf.get("Statistic.totalIncorrect");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return result;
	}

	@Override
	public Map<String, Integer[]> getStatisticUser(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		Map<String, Integer[]> statistic = new HashMap<String, Integer[]>();
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
					Integer[] sports = { rs.getInt("correctsports"),
							rs.getInt("totalsports") };
					Integer[] shows = { rs.getInt("correctshows"),
							rs.getInt("totalshows") };
					Integer[] science = { rs.getInt("correctscience"),
							rs.getInt("totalscience") };
					Integer[] art = { rs.getInt("correctart"),
							rs.getInt("totalart") };
					Integer[] geo = { rs.getInt("correctgeography"),
							rs.getInt("totalgeography") };
					Integer[] history = { rs.getInt("correcthistory"),
							rs.getInt("totalhistory") };

					statistic.put("SPORTS", sports);
					statistic.put("SHOWS_AND_ENTERTAINMENT", shows);
					statistic.put("SCIENCE_AND_TECHNOLOGY", science);
					statistic.put("ART_AND_LITERATURE", art);
					statistic.put("GEOGRAPHY", geo);
					statistic.put("HISTORY", history);
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

}
