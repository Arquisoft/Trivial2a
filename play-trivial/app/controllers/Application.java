package controllers;

import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Trivial2A. Funcionando con Play"));
    }
    
//    public static Result showLogin() {
//		return ok(login.render(Form.form(Login.class)));
//	}
//
//	public static Result showRegister() {
//		return ok(register.render(Form.form(Register.class)));
//	}
//	
//	public static Result authenticate() {
//		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
//		if (loginForm.hasErrors()) {
//			return badRequest(login.render(loginForm));
//		} else {
//			session().clear();
//			session("id", loginForm.get().id);
//			
//			if(!loginForm.get().id.equals("admin"))
//				return redirect(routes.Juego.showIndex());
//			
//			return redirect(routes.Application.showAdminStatistics());
//		}
//	}

}
