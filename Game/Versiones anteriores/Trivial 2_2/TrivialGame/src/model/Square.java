package model;

/** Clase "Score" del modelo.
 * @author González Fernandez Cristian y Velázquez Vico álvaro
 * @version 1 - Last changes: -
 */

//This class will be the node of the graph, which will be implemented in the 
// logic package
public class Square {
	
	private int id;
	private Category category;
	private Question question;
	private TypeQuestion type;
	
	
	

	public Square(int id, Category category, Question question,
			TypeQuestion type) {
		super();
		this.id = id;
		this.category = category;
		this.question = question;
		this.type = type;
	}
	
	
	

	public Square(int id, Category category, TypeQuestion type) {
		super();
		this.id = id;
		this.category = category;
		this.type = type;
	}




	public TypeQuestion getType() {
		return type;
	}

	public void setType(TypeQuestion type) {
		this.type = type;
	}

	public Square(int id, Category category){
		this.id = id;
		this.category = category;
	}
		
	public int getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion(){
		return question;
	}
	
}
