package controllers;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebeaninternal.util.DefaultExpressionList;
import model.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import static play.data.Form.form;
import play.libs.*;
import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;

public class Application extends Controller {

    public static Result index() {
        List<Goods> goodsList = Goods.find.all();
        return ok(index.render(goodsList));
    }

    public static Result cart() {
        List<Goods> goodsList = Goods.find.all();
        return ok(cart.render(goodsList));
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

    // вюшкa 404

    public static Result error() { return ok(error.render(""));}

    public static Result partners() { return ok(partners.render(""));}

    @Security.Authenticated(Secured.class)
    public static Result checkout() {
        return ok(checkout.render(""));
    }

    public static Result shop() {
        List<Goods> goodsList = Goods.find.all();
        return ok(shop.render(goodsList));
    }

    public static Result registration_get() { return ok(registration.render(form(Register.class))); }

    public static Result productdetails(Integer id) {
        List<Goods> goodsList = Goods.find.all();
        return ok(productdetails.render(goodsList , id ,form(Comment.class)));}

    //Ворма входа в систему
    public static Result login() {

        if(session("email") !=null && !session("email").isEmpty())
            return redirect(controllers.routes.Application.index());

        Email email = new Email();
        email.setSubject("Simple email");
        email.setFrom("Mister FROM <1asylkhan@mail.ru>");
        email.addTo("Miss TO <1asylkhan@mail.ru>");
adds attachment
        email.addAttachment("attachment.pdf", new File("/some/path/attachment.pdf"));
adds inline attachment from byte array
        email.addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE);
sends text, HTML or both...
        email.setBodyText("http://localhost/url/num");
        email.setBodyHtml("<html><body><p>An <b>html</b> message</p></body></html>");
        email.setBodyHtml("<a href='http://localhost:9000/url/num'>http://localhost/url/num</a>");
        MailerPlugin.send(email);
        return ok(login.render(form(Login.class)));
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(controllers.routes.Application.login());
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
                if(!accounts.path.equals(md5(password)))
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
            //session().clear();
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
    /**
     * Форма для регистрации
     * */
    public static class Comment {

        public String comment;

        //Функция валидации
        public String validate() {

            if (session("email")==null || session("email").isEmpty()) {
                return "Вы не авторизованы, чтобы оставить коментарий авторизуйтесь";
            }
            if (comment ==null || comment.isEmpty()) {
                return "Вы не написали коментарий";
            }

            return null;
        }
    }

    public static Result registration() {
        Form<Register> regForm = Form.form(Register.class).bindFromRequest();
        if (regForm.hasErrors()) {
            return badRequest(registration.render(regForm));

        } else {

            Accounts accounts = new Accounts();
            accounts.name= regForm.get().name;
            accounts.email= regForm.get().email;
            accounts.path= md5(regForm.get().password);
            accounts.accounttype = AccountType.USER;
            accounts.save();

            session().clear();
            //session("email", regForm.get().email);
            return redirect(routes.Application.login());
        }
    }

    public static Result productcoments(Integer id) {
        Form<Comment> commentForm = Form.form(Comment.class).bindFromRequest();
        if (commentForm.hasErrors()) {
            List<Goods> goodsList = Goods.find.all();
            return badRequest(productdetails.render(goodsList , id ,commentForm));

        } else {

            Comments comments = new Comments();
            String email =  session("email");
            Accounts accounts = Accounts.findByName(email);

            comments.account_id = accounts.id;
            comments.comment = commentForm.get().comment;
            comments.goods_id = id;
            comments.status = model.Status.OFF;
            comments.date = new Timestamp(Calendar.getInstance().getTime().getTime());
            comments.save();
            return redirect(routes.Application.login());
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

    public static class Basket {
        public String id;
        public String count;
    }

    public static Result addBasketPost() {
        Form<Basket> basketForm = Form.form(Basket.class).bindFromRequest();

        String uid = "basket_"+ basketForm.get().id;
        if(session(uid)==null)
            session(uid, basketForm.get().count);
        else{
            String oldValue = session(uid);
            Integer newValue = Integer.valueOf(oldValue) + Integer.valueOf(basketForm.get().count);
            session(uid, String.valueOf(newValue));
        }

        flash("Successfully  uploaded" + request().body());
        return ok("Successfully  uploaded" +  session(uid));
    }


    public static Result basketCount() {

        Integer basketCount = 0;

        for(String key : session().keySet()){
            if(key.startsWith("basket_")){
                basketCount+=Integer.valueOf(session(key));
            }

        }


        return ok(String.valueOf(basketCount));
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
