package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import conf.Conf;
import conf.Jdbc;
import persistence.StatisticDao;

public class StatisticJdbcDao implements StatisticDao {

	@Override
	public Map<Integer, Integer> getStatisticSports() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getSports");

		Map<Integer, Integer> sports = new HashMap<Integer, Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				sports.put(rs.getInt(1), rs.getInt(2));
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
	public Map<Integer, Integer> getStatisticShows() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getShows");

		Map<Integer, Integer> shows = new HashMap<Integer, Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			while(rs.next()){
				shows.put(rs.getInt(1), rs.getInt(2));
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
	public Map<Integer, Integer> getStatisticScience() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getScience");

		Map<Integer, Integer> science = new HashMap<Integer, Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			while(rs.next()){
				science.put(rs.getInt(1), rs.getInt(2));
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
	public Map<Integer, Integer> getStatisticArt() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getArt");

		Map<Integer, Integer> art = new HashMap<Integer, Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				art.put(rs.getInt(1), rs.getInt(2));
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
	public Map<Integer, Integer> getStatisticGeography() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getGeography");

		Map<Integer, Integer> geo = new HashMap<Integer, Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			while(rs.next()){
				geo.put(rs.getInt(1), rs.getInt(2));
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
	public Map<Integer, Integer> getStatisticHistory() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getHistory");

		Map<Integer, Integer> history = new HashMap<Integer, Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				history.put(rs.getInt(1), rs.getInt(2));
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
	public Map<String, Map<Integer, Integer>> getAllStatistics() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String SQL = Conf.get("Statistic.getAll");
		
		Map<String,Map<Integer, Integer>> statistic = new HashMap<String,Map<Integer, Integer>>();
		Map<Integer, Integer> sports = new HashMap<Integer, Integer>();
		Map<Integer, Integer> shows = new HashMap<Integer, Integer>();
		Map<Integer, Integer> science = new HashMap<Integer, Integer>();
		Map<Integer, Integer> art = new HashMap<Integer, Integer>();
		Map<Integer, Integer> geo = new HashMap<Integer, Integer>();
		Map<Integer, Integer> history = new HashMap<Integer, Integer>();
		try {
			con = Jdbc.getConnection();
			ps = con.prepareStatement(SQL);
			rs = ps.executeQuery();
			if(rs.next()){
				sports.put(rs.getInt(1), rs.getInt(2));
				shows.put(rs.getInt(3),rs.getInt(4));
				science.put(rs.getInt(5),rs.getInt(6));
				art.put(rs.getInt(7), rs.getInt(8));
				geo.put(rs.getInt(9), rs.getInt(10));
				history.put(rs.getInt(11), rs.getInt(12));
				statistic.put("sports", sports);
				statistic.put("shows", shows);
				statistic.put("science",science);
				statistic.put("art",art);
				statistic.put("geography",geo);
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

}
