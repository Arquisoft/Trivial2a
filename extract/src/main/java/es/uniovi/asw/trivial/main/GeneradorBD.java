package es.uniovi.asw.trivial.main;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.uniovi.asw.trivial.model.Question;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class GeneradorBD {

	public List<Question> preguntas;

	private static final String filePath = System.getProperty("user.dir")
			+ "/src/salida.txt";

	private void JsonReader() {
		try {
			// read the json file
			Gson gson = new Gson();
			FileReader reader = new FileReader(filePath);			
			JsonReader jsonReader = new JsonReader(reader);
			jsonReader.setLenient(true);
			
			//JsonParser parser = new JsonParser();
		    JsonElement jsonElement = null;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void savePreguntas() {
		JsonReader();
	}

}
