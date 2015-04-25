package es.uniovi.asw.trivial.persistencia.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.uniovi.asw.trivial.conf.Conf;
import es.uniovi.asw.trivial.conf.Jdbc;
import es.uniovi.asw.trivial.persistencia.AnswerDao;

public class AnswerJdbcDao implements AnswerDao {

	@Override
	public void saveCorrect(int id,String answer) {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		try {
			String sql = Conf.get("Answer.saveCorrect");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, answer);
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("No se pudo guardar la respuesta");
			} else
				con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(ps);
			Jdbc.close(con);
		}

	}

	@Override
	public void saveIncorrect(int id, String answer) {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		try {
			String sql = Conf.get("Answer.saveIncorrect");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, answer);
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("No se pudo guardar la respuesta");
			} else
				con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(ps);
			Jdbc.close(con);
		}
		
	}

}
