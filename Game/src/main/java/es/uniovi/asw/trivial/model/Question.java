package es.uniovi.asw.trivial.model;

import java.util.ArrayList;
import java.util.List;

/** Clase "Question" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 1 - Last changes: -
 */
public class Question {
	
	private String statement;
	private String correctAnswer;
	private List<String> incorrectAnswers = new ArrayList<String>();
	
	private String category;
	
	//Getters and setters
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
