package model;

import play.data.Form;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Transactionprocess extends Model {
    @Id
    public Integer id;
    public String name;
    public String description;

    public static Finder<Integer, Transactionprocess> find = new Finder<Integer, Transactionprocess>(Integer.class, Transactionprocess.class);

    public static List<Transactionprocess> all(){
        return all();
    }

    public Transactionprocess(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static Transactionprocess create(String name, String description) {
        Transactionprocess transactionprocess = new Transactionprocess(name, description);
        transactionprocess.save();
        return transactionprocess;
    }

    public static void create(Transactionprocess transactionprocess) {
        transactionprocess.save();
    }

    public static Transactionprocess findByName(String name){
        return find.where().eq("name",name).findUnique();
    }

    public static void delete(Integer id) {
        find.ref(id).delete();
    }

    public void update(Form<Transactionprocess> transactionprocessForm){
        Transactionprocess updForm = transactionprocessForm.get();
        this.name = updForm.name;
        this.save();
    }
}