package model;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Goods extends Model {

    @Id
    public Integer id;
    public String name;
    public String description;


}
