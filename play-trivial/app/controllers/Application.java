package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import factories.BusinessFactory;
import bussines.GameAPI;
import bussines.exceptions.IllegalActionException;
import bussines.gameClasses.BoardOptionsFactory;
import bussines.StatisticsLoader;
import bussines.impl.GameApiImpl;
import play.Routes;
import play.mvc.*;
import play.cache.Cache;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.*;

public class Application extends Controller {

	static GameAPI api = new GameApiImpl();

	public static Result register() {
		DynamicForm registerForm = form().bindFromRequest();
		String id = registerForm.get("id");
		String password = registerForm.get("password");
		String passwordRp = registerForm.get("passwordRp");

		if (User.getUser(id, api)) {
			registerForm.reject("User already exists");
			return badRequest(signup.render(registerForm));
		} else if (!password.equals(passwordRp)) {
			registerForm
					.reject("You must enter the same password in both fields");
			return badRequest(signup.render(registerForm));
		} else {
			User.addUser(id, password, api);
			return redirect(routes.Application.showLogin());
		}
	}

	public static Result showSignUp() {
		return ok(signup.render(new DynamicForm()));
	}

	public static Result showLogin() {
		return ok(login.render(new DynamicForm()));
	}

	public static Result authenticate() {
		DynamicForm loginForm = form().bindFromRequest();
		String id = loginForm.get("id");
		String passwd = loginForm.get("password");

		if (User.authenticate(id, passwd) == null) {
			loginForm.reject("Invalid user or password");
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("user", id);
			if(id.equals("admin")){
				return redirect(routes.Application.showStatistics());
			} else {
				return redirect(routes.Application.showGameBoard());
			}
		}
	}
	
	public static Result showStatistics(){
		return showStatsGeo(); // Mostramos las de geo por defecto.
	}

	public static Result showGameBoard() {
		GameAPI api = BusinessFactory.getGameAPI();
		try {
			List<String> players = new ArrayList<String>();
			players.add(session("user"));
			api.startGame(players, BoardOptionsFactory.getBoardOption(0));
			Cache.set("api", api);
		} catch (IllegalActionException e) {
			return badRequest("Fallo al cargar boardoption" + e.getMessage());
		}
		return ok(game.render(""));
	}

	// Mapea las acciones a javascript
	public static Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(Routes.javascriptRouter("myJsRoutes",
				routes.javascript.Application.showGameBoard(),
				routes.javascript.Application.showQuestion(),
				routes.javascript.Application.answerQuestion(),
				routes.javascript.Application.playDice()));
	}
	
	public static Result playDice() {
		String valor = "";
		try {
			GameAPI api = (GameAPI) Cache.get("api");
			valor = api.rollDice() + "";
			session("currentDice", valor);
		} catch (IllegalActionException e) {
			return badRequest("Fallo al lanzar el dado");
		}
		return ok(valor);
	}

	public static Result showQuestion(Integer id) {
		GameAPI api = (GameAPI) Cache.get("api");
		try {
			api.movePlayerTo(id, session("user"));
			Question question = api.getQuestion(session("user"), id);
			session("currentQuestionId", question.getId() + "");
			session("currentCategory",question.getCategory().toString());
			return ok(formatQuestion(question));
		} catch (IllegalActionException e) {
			return badRequest("Movimiento no valido");
		}
	}	
	
	private static String formatQuestion(Question question) {
		// pasa las respuestas a una cadenas string
		// Nota: Sustituir cadena por json
		String result = "Pregunta " + question.getId() + "_";
		result += question.getStatement();
		Set<String> answers = new HashSet<String>(
				question.getIncorrectAnswers());
		answers.add(question.getCorrectAnswer());
		int n = 1;
		for (String answer : answers) {
			result += "_" + answer;
			session("currentAns"+n,answer);
			n++;
		}
		return result;
	}
	
	public static Result answerQuestion(Integer answerId) {
		// Comprueba la la respuesta y devuelve un positivo o falso
		String message="";
		GameAPI api = (GameAPI) Cache.get("api");
		try {
			int currentQuestionId = Integer.valueOf(session("currentQuestionId"));
			String answerValue = session("currentAns"+answerId);
			boolean correct = api.isAnswerCorrect(currentQuestionId, answerValue,
					session("user"), api.getPlayerLocation(session("user")));
			SquareType type = api.getSquareType(api.getPlayerLocation(session("user")));
			if(api.isFinished()) {
				System.out.println("Victoria");
				return ok(login.render(new DynamicForm())); // deberia llevar a una pagina de enhorabuena
			}
			message = scoreMessage(correct,api.getPlayerLocation(session("user")),type);
		} catch (IllegalActionException e) {
			e.printStackTrace();
			return badRequest("Ha ocurrido un fallo procesando la peticion");
		}
		return ok(message);
	}
	
	private static String scoreMessage(boolean correct, int location,SquareType type){
		String message = location + "_";
		String[] currentCategory = session("currentCategory").split("_");
		String squareMessage ="point";
		if(type.equals(SquareType.GAME_PIECE))
			squareMessage = currentCategory[0].toLowerCase();
		message += correct ? squareMessage : "wrong";
		return message;
		
	}

	public static Result showStats(String category, List<Object[]> dato) {
		String id = session("user");
		
		if(id.equals("admin"))
			return ok(statisticsAdmin.render(category, dato));
		else
			return ok(statistics.render(category, filtrarLista(dato)));
	}
	
	public static List<Object[]> filtrarLista(List<Object[]> dato)
	{
		List<Object[]> filtrada = new ArrayList<>();
		String id = session("user");
		for(Object[] estadistica : dato)
		{
			if(estadistica[0].equals(id))
				filtrada.add(estadistica);
		}
		
		return filtrada;
	}

	public static Result showStatsGeo() {
		return showStats("Geografía", getStatisticsCategory("GEOGRAPHY"));
	}

	public static Result showStatsShows() {
		return showStats("Espectáculos", getStatisticsCategory("SHOWS_AND_ENTERTAINMENT"));
	}

	public static Result showStatsHistory() {
		return showStats("Historia", getStatisticsCategory("HISTORY"));
		
	}

	public static Result showStatsArtLit() {
		return showStats("Arte y Literatura", getStatisticsCategory("ART_AND_LITERATURE"));
	}

	public static Result showStatsScience() {
		return showStats("Ciencias y Naturaleza", getStatisticsCategory("SCIENCE_AND_TECHNOLOGY"));
	}

	public static Result showStatsSports() {
		// FIXME: Provisional
		return showStats("Deportes", getStatisticsCategory("SPORTS"));
	}
	
	private static List<Object[]> getStatisticsCategory(String category)
	{
		return getStatisticsLoader().getInfoQuestionsByCategory(category);
	}
	
	private static StatisticsLoader getStatisticsLoader()
	{
		return BusinessFactory.getStatisticsLoader();
	}
	
	public static GameAPI getGameAPI()
	{
		return BusinessFactory.getGameAPI();
		
	}

	public static Result logout() {
		session().clear();
		session().remove("user");
		return redirect(routes.Application.showLogin());
	}
}
