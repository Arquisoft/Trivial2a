package es.uniovi.asw.trivial.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
	
	private int id;
	private Category category;

	private String statement;
	private String correctAnswer;
	private List<String> incorrectAnswers = new ArrayList<String>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public List<String> getIncorrectAnswers() {
		return incorrectAnswers;
	}
	public void setIncorrectAnswers(List<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

}
