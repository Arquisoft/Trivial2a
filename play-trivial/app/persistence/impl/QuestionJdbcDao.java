package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Question;
import persistence.QuestionDao;
import bussines.gameClasses.Category;
import conf.Conf;
import conf.Jdbc;

public class QuestionJdbcDao implements QuestionDao {

	@Override
	public List<Question> getQuestions() {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("Question.getQuestions");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Question> questions = new ArrayList<Question>();

			while (rs.next()) {
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setStatement(rs.getString("statement"));
				question.setCorrectAnswer(getCorrectAnswer(question.getId()));
				question.setIncorrectAnswers(getWrongAnswers(question.getId()));
				question.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));

				questions.add(question);
			}
			return questions;

		} catch (SQLException e) {
			return null;
		} finally {
			Jdbc.close(rs, ps);
			Jdbc.close(con);
		}
	}

	@Override
	public void save(Question question) {
		saveQuestion(question);
		question.setId(getQuestionId(question));
		saveCorrectAnswer(question);
		saveWrongAnswers(question);
	}

	private int getQuestionId(Question question) {
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

	@Override
	public Question getQuestionByCategory(Category category) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			String sql = Conf.get("Question.getByCategory");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, category.toString());
			rs = ps.executeQuery();
			List<Question> questions = new ArrayList<Question>();

			while (rs.next()) {
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setStatement(rs.getString("statement"));
				question.setCorrectAnswer(getCorrectAnswer(question.getId()));
				question.setIncorrectAnswers(getWrongAnswers(question.getId()));
				question.setCategory(Category.valueOf(rs.getString("category")));

				questions.add(question);
			}
			Random r = new Random();
			return questions.get(r.nextInt(questions.size()));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			Jdbc.close(rs, ps);
			Jdbc.close(con);
		}
	}

	@Override
	public void deleteAll() {
		PreparedStatement ps = null;
		Connection con = null;
		try {
			String sql = Conf.get("Question.deleteAll");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(ps);
			Jdbc.close(con);
		}
	}

	private void saveCorrectAnswer(Question question) {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		try {
			String sql = Conf.get("Question.saveAnswer");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, question.getId());
			ps.setBoolean(2, true);
			ps.setString(3, question.getCorrectAnswer());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException(
						"No se pudo guardar las respuestas correctas");
			} else
				con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(ps);
			Jdbc.close(con);
		}
	}

	private void saveWrongAnswers(Question question) {
		for (String wrong : question.getIncorrectAnswers()) {
			saveWrong(question.getId(), wrong);
		}
	}

	private void saveWrong(int questionId, String wrong) {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		try {

			String sql = Conf.get("Question.saveAnswer");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, questionId);
			ps.setBoolean(2, false);
			ps.setString(3, wrong);
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new SQLException(
						"No se pudo guardar las respuestas incorrectas");
			} else
				con.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(ps);
			Jdbc.close(con);
		}
	}

	// private int getNewAnswerId() {
	// PreparedStatement ps = null;
	// Connection con = null;
	// ResultSet rs = null;
	// String sql = Conf.get("Question.getMaxAnswerID");
	//
	// try {
	//
	// con = Jdbc.getConnection();
	// ps = con.prepareStatement(sql);
	// rs = ps.executeQuery();
	// int id;
	//
	// rs.next();
	// id = rs.getInt(1);
	// return id;
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return 0;
	// } finally {
	// Jdbc.close(rs, ps);
	// Jdbc.close(con);
	// }
	// }
	//
	// private int getNewQuestionId() {
	// PreparedStatement ps = null;
	// Connection con = null;
	// ResultSet rs = null;
	// String sql = Conf.get("Question.getMaxID");
	//
	// try {
	//
	// con = Jdbc.getConnection();
	// ps = con.prepareStatement(sql);
	// rs = ps.executeQuery();
	// int id;
	//
	// rs.next();
	// id = rs.getInt(1);
	// return id;
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// return 0;
	// } finally {
	// Jdbc.close(rs, ps);
	// Jdbc.close(con);
	// }
	// }

	private String getCorrectAnswer(int questionId) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("Question.getCorrectAnswer");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, questionId);
			rs = ps.executeQuery();
			String answer = "";

			if (rs.next()) {
				answer = rs.getString("text");
			}
			return answer;

		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		} finally {
			Jdbc.close(rs, ps);
			Jdbc.close(con);
		}
	}

	private List<String> getWrongAnswers(int questionId) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("Question.getWrongAnswers");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, questionId);
			rs = ps.executeQuery();
			List<String> answers = new ArrayList<String>();

			while (rs.next()) {
				answers.add(rs.getString("text"));
			}
			return answers;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, ps);
			Jdbc.close(con);
		}
		return null;
	}
}

