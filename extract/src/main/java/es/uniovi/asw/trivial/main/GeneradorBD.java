package es.uniovi.asw.trivial.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.uniovi.asw.trivial.model.Category;
import es.uniovi.asw.trivial.model.Question;
import es.uniovi.asw.trivial.persistencia.AnswerDao;
import es.uniovi.asw.trivial.persistencia.PersistenceServices;
import es.uniovi.asw.trivial.persistencia.QuestionDao;
import es.uniovi.asw.trivial.persistencia.impl.QuestionJdbcDao;
import es.uniovi.asw.trivial.persistencia.impl.SimplePersistenceFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GeneradorBD {

	public List<Question> preguntas = new ArrayList<Question>();

	private static final String filePath = System.getProperty("user.dir")
			+ "/src/salida.txt";

	private void JsonReader() {
		try {
			// read the json file
			JsonParser parser = new JsonParser();
		    JsonElement jsonElement = null;
			FileReader reader = new FileReader(filePath);
			jsonElement = parser.parse(reader);
			JsonArray array = jsonElement.getAsJsonArray();
			for (int i =0; i<array.size()-1; i++)
			{
				JsonElement e = array.get(i);		
				addToList(e.getAsJsonObject());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addToList(JsonObject o) {
		Question q = new Question();
		q.setId(o.get("question").getAsString().charAt(1));
		q.setStatement(o.get("text").getAsString());		
		q.setCategory(Category.valueOf(o.get("category").getAsString().replace(" ", "").replace(".", "_")));
		q.setCorrectAnswer(o.get("correct").getAsString());
		List<String> incorrect = new ArrayList<String>();
		JsonArray incorrectas = o.get("incorrect").getAsJsonArray();
		incorrect.add(incorrectas.get(0).getAsString());
		incorrect.add(incorrectas.get(1).getAsString());
		q.setIncorrectAnswers(incorrect);
		preguntas.add(q);
		
	}

	public void savePreguntas() {
		System.out.print("Almacenando preguntas en BD...");
		JsonReader();
		PersistenceServices factory = new SimplePersistenceFactory();	
		AnswerDao a = factory.createAnswerDao();
		QuestionDao q = factory.createQuestionDao();
		for (Question pregunta : preguntas){
			q.save(pregunta);
			int id = q.getId(pregunta);
			a.saveCorrect(id, pregunta.getCorrectAnswer());
			for (String respuesta: pregunta.getIncorrectAnswers()){
				a.saveIncorrect(id, respuesta);
			}
			
		}
		System.out.println("FIN");
}
	
	

}
