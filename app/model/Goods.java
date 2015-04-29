package model;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Goods extends Model {

    @Id
    @SequenceGenerator(name="gen", sequenceName="goods_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    public Integer id;
    public String name;
    public Double price;
    public String description;

    //Елемент для создания запроса к accounts
    public static Finder<Integer,Goods> find = new Finder<Integer,Goods>( Integer.class, Goods.class );


}
