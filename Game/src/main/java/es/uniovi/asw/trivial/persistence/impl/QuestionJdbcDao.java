package es.uniovi.asw.trivial.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.trivial.conf.Conf;
import es.uniovi.asw.trivial.conf.Jdbc;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.persistence.QuestionDao;

public class QuestionJdbcDao implements QuestionDao {

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

				questions.add(question);
			}
			return questions;

		} catch (SQLException e) {
			return null;
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	public void save(Question question) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		question.setId(getNewQuestionId());
		try {

			String sql = Conf.get("Question.saveQuestion");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(0, question.getId());
			ps.setString(1, question.getStatement());
			rs = ps.executeQuery();
			
			saveCorrectAnswer(question);
			saveWrongAnswers(question);

		} catch (SQLException e) {
			
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	private void saveCorrectAnswer(Question question) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			String sql = Conf.get("Question.saveCorrectAnswer");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(0, getNewAnswerId());
			ps.setInt(1, question.getId());
			ps.setString(2, question.getCorrectAnswer());
			rs = ps.executeQuery();

		} catch (SQLException e) {
			
		} finally {
			Jdbc.close(rs, ps);
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
		ResultSet rs = null;
		try {
			
			String sql = Conf.get("Question.saveWrongAnswers");
			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(0, getNewAnswerId());
			ps.setInt(1, questionId);
			ps.setString(2, wrong);
			rs = ps.executeQuery();

		} catch (SQLException e) {
			
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	private int getNewAnswerId() {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("Question.getMaxAnswerID");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int id;

			rs.next();
			id = rs.getInt(1);
			return id;

		} catch (SQLException e) {
			return 0;
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	private int getNewQuestionId() {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("Question.getMaxID");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int id;

			rs.next();
			id = rs.getInt(1);
			return id;

		} catch (SQLException e) {
			return 0;
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	private String getCorrectAnswer(int questionId) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("Question.getCorrectAnswer");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(0, questionId);
			rs = ps.executeQuery();
			String answer;

			rs.next();
			answer = rs.getString("text");
			return answer;

		} catch (SQLException e) {
			return "";
		} finally {
			Jdbc.close(rs, ps);
		}
	}

	private List<String> getWrongAnswers(int questionId) {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = Conf.get("Question.getWrongAnswer");

		try {

			con = Jdbc.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(0, questionId);
			rs = ps.executeQuery();
			List<String> answers = new ArrayList<String>();

			while (rs.next()) {
				answers.add(rs.getString("text"));
			}
			return answers;

		} catch (SQLException e) {
		} finally {
			Jdbc.close(rs, ps);
		}
		return null;
	}
}

