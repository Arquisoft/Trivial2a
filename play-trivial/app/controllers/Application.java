package controllers;

import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result showLogin() {
    	//FIXME: Metodo provisional de login
        return ok(login.render("")); 
    }
    
    public static Result showSignUp() {
    	//FIXME: Metodo provisional de sign up
        return ok(signup.render(""));
    }

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
