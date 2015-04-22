package controllers;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebeaninternal.util.DefaultExpressionList;
import model.Accounts;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.util.List;

import static play.data.Form.form;

public class Application extends Controller {

    /**
     * Форма для проверки логина и пароля
     * */
    public static class Login {
        public String email;
        public String password;

        //Функция валидации
        public String validate() {
            if (email ==null || email.isEmpty()) {
                return "Invalid user or password";
            }
            List<Accounts> accountsList =  Accounts.find.where().eq("email", email).findList();
            if(accountsList.isEmpty())
                return "Пользовать с таким паролем отсутствует";

            if(!accountsList.isEmpty()){
                Accounts accounts = accountsList.get(0);
                if(!accounts.path.equals(password))
                    return "Неверный пароль";
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

    //Ворма входа в систему
    public static Result login() {
        return ok(login.render(form(Login.class)));
    }

    //Функция проверки логина и пароля
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


