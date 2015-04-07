package es.uniovi.asw.trivial.model;

import java.util.ArrayList;
import java.util.List;

/** Clase "Question" del modelo.
 * @author Gonz�lez Fernandez Cristian y Vel�zquez Vico �lvaro
 * @version 2 - Last changes: - ID added
 */
public class Question {
	
	private int id;
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
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
}
