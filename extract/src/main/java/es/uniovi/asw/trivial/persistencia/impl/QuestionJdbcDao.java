package es.uniovi.asw.trivial.persistencia.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uniovi.asw.trivial.conf.Conf;
import es.uniovi.asw.trivial.conf.Jdbc;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.persistencia.QuestionDao;

public class QuestionJdbcDao implements QuestionDao {

	@Override
	public void save(Question question) {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		try {
			String sql = Conf.get("Question.save");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, question.getStatement());
			ps.setString(2,question.getCategory().toString());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException("No se pudo guardar la pregunta "
						+ question.getId());
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
	public int getId(Question question) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int id = 0;
		try {

			String sql = Conf.get("Question.id");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, question.getStatement());
			rs = ps.executeQuery();
			rs.next();
			id = rs.getInt(1);
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			Jdbc.close(ps);
			Jdbc.close(con);
		}
	}	

}