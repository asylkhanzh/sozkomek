package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result cart() {
        return ok(cart.render("Your new application is ready."));
    }

    public static Result login() {
        return ok(login.render("Your new application is ready."));
    }

    public static Result checkout() {
        return ok(checkout.render("Your new application is ready."));
    }
    public static Result shop() {
        return ok(shop.render("Your new application is ready."));
    }

}
