package model;

import java.util.List;
import play.data.Form;
import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transactiontype extends Model {

    @Id
    public Integer id;
    public String name;
    public String description;

    public static Finder<Integer, Transactiontype> find = new Finder<Integer, Transactiontype>(Integer.class, Transactiontype.class);

    public static List<Transactiontype> all(){
        return all();
    }

    public Transactiontype(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Transactiontype create(String name, String description) {
        Transactiontype transactiontype = new Transactiontype(name, description);
        transactiontype.save();
        return transactiontype;
    }

    public static void create(Transactiontype transactiontype) {
        transactiontype.save();
    }

    public static Transactiontype findByName(String name){
        return find.where().eq("name",name).findUnique();
    }

    public static void delete(Integer id) {
        find.ref(id).delete();
    }

    public void update(Form<Transactiontype> transactiontypeForm){
        Transactiontype updForm = transactiontypeForm.get();
        this.name = updForm.name;
        this.save();
    }
}