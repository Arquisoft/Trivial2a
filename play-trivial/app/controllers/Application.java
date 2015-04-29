package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;


public class Application extends Controller {

	public static Result showLogin() {
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			System.out.println("Entrando en sesion como " + loginForm.get().id);
			session().clear();
			session("id", loginForm.get().id);

			return redirect("/game");
		}
	}

	public static Result showSignUp() {
		// FIXME: Metodo provisional de sign up
		return ok(signup.render(""));
	}

	public static Result showGameBoard() {
		// FIXME: Metodo provisional de juego, muestra el cuadro de pregunta si
		// existe una
		return ok(game.render("", true));

	}

	public static Result getNumber() {
		Random r = new Random(10);
		return ok(r.nextInt() + "");
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
}
