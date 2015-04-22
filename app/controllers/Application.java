package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public static class Login {
        public String email;
        public String password;

        public String validate() {
            if (email ==null || email.isEmpty()) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result cart() {
        return ok(cart.render("Your new application is ready."));
    }

    public static Result login() {
        return ok(login.render(form(Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        //if (loginForm.hasErrors()) {
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));

        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(routes.Application.index());
        }
    }



    public static Result checkout() {
        return ok(checkout.render("Your new application is ready."));
    }
    public static Result shop() {
        return ok(shop.render("Your new application is ready."));
    }



}


