package controllers;

import java.util.ArrayList;
import java.util.List;

import bussines.GameAPI;
import bussines.impl.GameApiImpl;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import models.*;
import views.html.*;

public class Application extends Controller {
	
	private static GameAPI api = new GameApiImpl();
		
	public static Result register() {
		DynamicForm registerForm = form().bindFromRequest();
		String id = registerForm.get("id");
		String password = registerForm.get("password");
		String passwordRp = registerForm.get("passwordRp");
		
		if (User.getUser(id, api)) {
			registerForm.reject("User already exists");
			return badRequest(signup.render(registerForm));
		} else if(!password.equals(passwordRp)){
			registerForm.reject("You must enter the same password in both fields");
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
			return redirect(routes.Application.showGameBoard());
		}
	}

	public static Result showGameBoard() {
		// FIXME: Metodo provisional de juego, muestra el cuadro de pregunta si
		// existe una
		return ok(game.render(""));

	}

	// Mapea las acciones a javascript	
	public static Result javascriptRoutes() {
//	    response().setContentType("text/javascript");
//	    return ok(
//	        Routes.javascriptRouter("myJsRoutes",
//	            routes.javascript.Application.showGameBoard(),
//	            routes.javascript.Application.showQuestion()
//	            ));
		return null;
	}
	
	public static Result showQuestion(){
		// Simula solicitar y devolver una pregunta, 
		return ok(System.currentTimeMillis()%100 + "");		
	}

	public static Result showStats(String category, List<Object[]> dato) {
		// FIXME: Metodo provisional de ver stats

		List<Object[]> data = new ArrayList<Object[]>();
		Object[] o = { "Sam", 10, 3 };
		data.add(o);
		Object[] o2 = { "Luna", 20, 3 };
		data.add(o2);
		Object[] o3 = { "Neri", 20, 17 };
		data.add(o3);
		Object[] o4 = { "Crispin", 20, 17 };
		data.add(o4);
		return ok(statistics.render(category, data));
	}

	public static Result showStatsGeo() {
		// FIXME: Provisional
		return showStats("Geografía", null);
	}

	public static Result showStatsShows() {
		// FIXME: Provisional
		return showStats("Espectáculos", null);
	}

	public static Result showStatsHistory() {
		// FIXME: Provisional
		return showStats("Historia", null);
	}

	public static Result showStatsArtLit() {
		// FIXME: Provisional
		return showStats("Arte y Literatura", null);
	}

	public static Result showStatsScience() {
		// FIXME: Provisional
		return showStats("Ciencias y Naturaleza", null);
	}

	public static Result showStatsSports() {
		// FIXME: Provisional
		return showStats("Deportes", null);
	}
}
