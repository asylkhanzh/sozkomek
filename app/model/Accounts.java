package model;

import play.db.ebean.Model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * Created by админ on 21.04.2015.
 */
public class Accounts extends Model{

    @Id
    public Integer id;

    public String name;
    public String email;
    public String path;
    @Enumerated(EnumType.STRING)
    public AccountType accounttype;


}
