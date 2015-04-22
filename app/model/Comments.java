package model;

import com.sun.tracing.dtrace.ModuleName;
import play.db.ebean.Model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by админ on 22.04.2015.
 */
public class Comments extends Model {

    @Id
    public Integer id;

    @ManyToOne
    public Integer goods_id;

    @ManyToOne
    public Integer accounts_id;

    public String comment;

    public String status;
}
