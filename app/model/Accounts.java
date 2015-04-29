package model;

import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by админ on 21.04.2015.
 */
@Entity
public class Accounts extends Model{

    @Id
    @SequenceGenerator(name="gen", sequenceName="table_idcolumn_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    public Integer id;

    public String name;
    public String email;
    public String path;
    @Enumerated(EnumType.STRING)
    public AccountType accounttype;

    //Елемент для создания запроса к accounts
    public static Finder<Integer,Accounts> find = new Finder<Integer,Accounts>( Integer.class, Accounts.class );


}
