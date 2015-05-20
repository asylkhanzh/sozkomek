package controllers;

import model.AccountType;
import model.Comments;
import model.Goods;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.addGoods;
import views.html.confirmComments;
import views.html.confirmOrders;

import java.io.File;
import java.util.List;

import static play.data.Form.form;

/**
 * Created by админ on 30.04.2015.
 */
public class Adminpanel extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result addGoods() {
        if(!session("accounttype").equals(AccountType.ADMIN.name()))
            return redirect(routes.Application.index());
        return  ok(addGoods.render(form(GoodsForm.class)));
    }

    @Security.Authenticated(Secured.class)
    public static Result confirmComments() {
        if(!session("accounttype").equals(AccountType.ADMIN.name()))
            return redirect(routes.Application.index());
        List<Comments> commentsList = Comments.find.all();
        return ok(confirmComments.render(commentsList));
    }

    @Security.Authenticated(Secured.class)
    public static Result confirmCommentsID(Integer id) {
        if(!session("accounttype").equals(AccountType.ADMIN.name()))
            return redirect(routes.Application.index());
        List<Comments> commentsList = Comments.find.all();

        Comments comment = Comments.find.byId(id);
        comment.status = model.Status.ON;
        comment.update();

        return ok(confirmComments.render(commentsList));
    }

    @Security.Authenticated(Secured.class)
    public static Result confirmOrders() {
        if(!session("accounttype").equals(AccountType.ADMIN.name()))
            return redirect(routes.Application.index());
        return ok(confirmOrders.render(""));
    }



    public static class GoodsForm {
        public String name;
        public Double price;
        public String description;
        public String method;

        //Функция валидации
        public String validate() {

            List<Goods> goodsList =  Goods.find.where().eq("name", name).findList();
            if(!goodsList.isEmpty())
                return "Товар с таким именем существует";
            if (name ==null || name.isEmpty()) {
                return "Не указали НАИМЕНОВАНИЕ товара";
            }
            if (price ==null || price<0) {
                return "Не указали ЦЕНУ товара";
            }
            if (method ==null || method.isEmpty()) {
                return "Не выбрали СПОСОБ доставки товара";
            }
            if (description ==null || description.isEmpty()) {
                return "Не описали товар";
            }
            return null;
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result addPostGoods() {
        if(!session("accounttype").equals(AccountType.ADMIN.name()))
            return redirect(routes.Application.index());
        Form<GoodsForm> regForm = Form.form(GoodsForm.class).bindFromRequest();
        if (regForm.hasErrors()) {
            return badRequest(addGoods.render(regForm));

        } else {

            Goods goods = new Goods();
            goods.name= regForm.get().name;
            goods.price= regForm.get().price;
            goods.description=regForm.get().description;
            goods.method=regForm.get().method;
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
        }
    }

}
