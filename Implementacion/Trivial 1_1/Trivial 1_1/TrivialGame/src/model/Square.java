package model;

/** Clase "Score" del modelo.
 * @author González Fernandez Cristian y Velázquez Vico álvaro
 * @version 1 - Last changes: -
 */

//This class will be the node of the graph, which will be implemented in the 
// logic package
public class Square {
	
	private int id;
	private String category;
	private Question question;
	private boolean isBigSquare; //boolean that decide if the square gives you points.
	
	
	public boolean isBigSquare() {
		return isBigSquare;
	}

	public void setBigSquare(boolean isBigSquare) {
		this.isBigSquare = isBigSquare;
	}

	public Square(int id, String category){
		this.id = id;
		this.category = category;
	}
		
	public int getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion(){
		return question;
	}
	
}
