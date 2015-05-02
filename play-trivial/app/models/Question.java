package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase "Question" del modelo.
 * 
 * @author Gonz�lez Fernandez Cristian
 * @author Vel�zquez Vico �lvaro
 * @author Montero Hernández, José Antonio
 */
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

	public List<String> getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(List<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getStatement() {
		return statement;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isCorrectAnswer(String answer) {
		if (correctAnswer.equals(answer))
			return true;
		return false;
	}
}
