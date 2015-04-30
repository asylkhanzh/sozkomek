package controllers;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebeaninternal.util.DefaultExpressionList;
import model.AccountType;
import model.Accounts;
import model.Goods;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.util.List;
import static play.data.Form.form;
import play.libs.*;
import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(""));
    }

    public static Result cart() {
        return ok(cart.render(""));
    }

    // вюшки О bimash

    public static Result productbimash() { return ok(productbimash.render(""));}

    public static Result obimash() { return ok(obimash.render(""));}

    public static Result contactbimash() { return ok(contactbimash.render(""));}

    // вюшки Сервис

    public static Result faqs() { return ok(faqs.render(""));}

    public static Result scheme() { return ok(scheme.render(""));}

    public static Result activate() { return ok(activate.render(""));}

    public static Result support() { return ok(support.render(""));}

    // вюшки 404

    public static Result error() { return ok(error.render(""));}

    public static Result productdetails() { return ok(productdetails.render(""));}

    @Security.Authenticated(Secured.class)
    public static Result checkout() {
        return ok(checkout.render(""));
    }

    public static Result shop() {
        List<Goods> goodsList = Goods.find.all();
        return ok(shop.render(goodsList));
    }

    @Security.Authenticated(Secured.class)
    public static Result addGoods() { return  ok(addGoods.render(form(GoodsForm.class))); }

    public static Result registration_get() { return ok(registration.render(form(Register.class))); }

    //Ворма входа в систему
    public static Result login() {

//        Email email = new Email();
//        email.setSubject("Simple email");
//        email.setFrom("Mister FROM <1asylkhan@mail.ru>");
//        email.addTo("Miss TO <1asylkhan@mail.ru>");
// adds attachment
//        email.addAttachment("attachment.pdf", new File("/some/path/attachment.pdf"));
// adds inline attachment from byte array
 //       email.addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE);
// sends text, HTML or both...
        //email.setBodyText("http://localhost/url/num");
        //email.setBodyHtml("<html><body><p>An <b>html</b> message</p></body></html>");
//        email.setBodyHtml("<a href='http://localhost:9000/url/num'>http://localhost/url/num</a>");
//        MailerPlugin.send(email);
        return ok(login.render(form(Login.class)));
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.Application.login()
        );
    }

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
                return "Пользовать с таким логином отсутствует";

            if(!accountsList.isEmpty()){
                Accounts accounts = accountsList.get(0);
                if(!accounts.path.equals(password))
                    return "Неверный пароль";
            }

            return null;
        }
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


            List<Accounts> accountsList =  Accounts.find.where().eq("email", loginForm.get().email).findList();
            Accounts accounts = accountsList.get(0);
            session("accounttype", accounts.accounttype.name());

            return redirect(routes.Application.index());
        }
    }

    /**
     * Форма для регистрации
     * */
    public static class Register {
        public String name;
        public String email;
        public String password;
        public String repassword;

        //Функция валидации
        public String validate() {


        List<Accounts> accountsList =  Accounts.find.where().eq("email", email).findList();
        if(!accountsList.isEmpty())
                return "Пользовать с таким логином существует";
            if (name ==null || name.isEmpty()) {
                return "имя не корректно";
            }
            if (email ==null || email.isEmpty()) {
                return "емайл не корректно";
            }
            if (password ==null || password.isEmpty()) {
                return "Введите пароль";
            }
            if (!password.equals(repassword)) {
                return "Пароль не совпадает";
            }



            return null;
        }
    }

    public static Result registration() {
        Form<Register> regForm = Form.form(Register.class).bindFromRequest();
        //if (loginForm.hasErrors()) {
        if (regForm.hasErrors()) {
            return badRequest(registration.render(regForm));

        } else {

            Accounts accounts = new Accounts();
            accounts.name= regForm.get().name;
            accounts.email= regForm.get().email;
            accounts.path= regForm.get().password;
            accounts.accounttype = AccountType.USER;
            accounts.save();

            session().clear();
            session("email", regForm.get().email);
            return redirect(routes.Application.index());
        }
    }

    public static class GoodsForm {
        public String name;
        public Double price;
        public String description;

        //Функция валидации
        public String validate() {

            List<Goods> goodsList =  Goods.find.where().eq("name", name).findList();
            if(!goodsList.isEmpty())
                return "Товар с таким именем существует";
            if (name ==null || name.isEmpty()) {
                return "не ввели емайл";
            }
            if (price ==null || price<0) {
                return "введите цену";
            }
            if (description ==null || description.isEmpty()) {
                return "не описали товар";
            }

            return null;
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result addPostGoods() {
        Form<GoodsForm> regForm = Form.form(GoodsForm.class).bindFromRequest();
        if (regForm.hasErrors()) {
            return badRequest(addGoods.render(regForm));

        } else {

            Goods goods = new Goods();
            goods.name= regForm.get().name;
            goods.price= regForm.get().price;
            goods.description=regForm.get().description;
            goods.save();


            Http.MultipartFormData body = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart picture = body.getFile("picture");
            if (picture != null) {
                String fileName = picture.getFilename();
                String contentType = picture.getContentType();
                File file = picture.getFile();

                String myUploadPath = Play.application().configuration().getString("myUploadPath");
                file.renameTo(new File(myUploadPath, goods.id + ".jpg"));
                return ok("File uploaded");
            } else {
                flash("error", "Missing file");
                return redirect(routes.Application.index());
            }

            //return redirect(routes.Application.index());
        }
    }
    public static Result addBasket(String id) {
        Goods goods = new Goods();
        goods.name=id;
        goods.price= 132.0;
        goods.description="test descr";
        goods.save();

        return ok("Successfully  uploaded");
    }

}


