package controllers;

import java.util.ArrayList;
import java.util.List;

import model.User;
import play.Routes;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.game;
import views.html.login;
import views.html.signup;
import views.html.statistics;
import factories.BusinessFactory;


public class Application extends Controller {

	public static Result showLogin() {
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("id", loginForm.get().id);

			return redirect("/game");
		}
	}
	
	public static Result register() {
		Form<Register> registerForm = Form.form(Register.class).bindFromRequest();
		if (registerForm.hasErrors()) {
			return badRequest(signup.render(registerForm));
		} else {
			System.out.println("Registrando jugador: " + registerForm.get().id);
			
			User.addUser(registerForm.get().id,
					registerForm.get().password);
			return redirect("/signup");
		}
	}

	public static Result showSignUp() {
		return ok(signup.render(Form.form(Register.class)));
	}

	public static Result showGameBoard() {
		// FIXME: Metodo provisional de juego, muestra el cuadro de pregunta si
		// existe una
		Cache.set("gameApi",BusinessFactory.getGameAPI() );
		return ok(game.render(""));
	}

	// Mapea las acciones a javascript	
	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("myJsRoutes",
	            routes.javascript.Application.showGameBoard(),
	            routes.javascript.Application.showQuestion(),
	            routes.javascript.Application.answerQuestion(),
	            routes.javascript.Application.playDice()
	            ));
	}
	
	public static Result playDice() {
		String valor = System.currentTimeMillis()%6 + "";
		session("currentDice",valor);
		return ok(valor); // valor del dado
	}
	
	public static Result showQuestion(Integer id){
		// Simula solicitar y devolver una pregunta, coincide tambien con la casilla actual 
		if(id>= 20)
			return badRequest("Movimiento no valido");
		session("currentQuestion",id+""); 
		return ok("Pregunta "+id+"_"
				+"¿En que ciudad vive Fernando Alonso?_"
				+ "Oviedo_"
				+ "Milan_"
				+ "Paris_"
				+ "Narnia"
				);	
		
	}
	
	// Comprueba la la respuesta y devuelve un positivo o falso
	public static Result answerQuestion(Integer answerId) {
		String currentQuestion = session("currentQuestion");
		return ok(currentQuestion+"_correcto");
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
	
	public static class Login {
		public String id;
		public String password;

		public String validate() {
		    if (User.authenticate(id, password) == null) {
		      return "Usuario o contraseña incorrectos";
		    }
		    return null;
		}
	}
	
	public static class Register {
		public String id = "Paula";
		public String password = "1";
		public String passwordRp = "1";

		public String validate() {
			if (!password.equals(passwordRp)) {
				return "Las dos contraseñas deben ser iguales";
			} else if (User.getUser(id)) {
				return "El ID de usuario introducido ya existe";
			}
			return null;
		}
	}
}
