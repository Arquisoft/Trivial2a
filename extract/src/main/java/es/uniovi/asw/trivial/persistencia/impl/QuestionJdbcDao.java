package es.uniovi.asw.trivial.persistencia.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.uniovi.asw.trivial.conf.Conf;
import es.uniovi.asw.trivial.conf.Jdbc;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.persistencia.QuestionDao;

public class QuestionJdbcDao implements QuestionDao {

	@Override
	public void save(Question question) {
		// TODO Auto-generated method stub
		
	}
	
	private void saveQuestion(Question question) {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		try {

			String sql = Conf.get("Question.saveQuestion");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, question.getStatement());
			ps.setString(2, question.getCategory().toString());
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

}