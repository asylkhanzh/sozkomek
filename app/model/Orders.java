package model;

import play.db.ebean.Model;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Orders extends Model{

    @Id
    public Long id;

    @ManyToOne
    public Accounts account;

    public Long amount;

//    @Column(name="date_create")
//    public Datelist dateCreate;

//    @Column(name="date_create", nullable=false)
//    public Date dateCreate;

//    @Column(name="date_pay")
//    public Datelist datePay;

//    @Column(name="date_pay")
//    public Date datePay;

    @ManyToOne
    @Column(name = "transactiontype_id")
    public Transactiontype transactiontype;

    @ManyToOne
    @Column(name = "transactionprocess_id")
    public Transactionprocess transactionprocess;

    @Column(name = "reference")
    public String reference;

    @Column(name = "approvalcode")
    public String approval_code;

    @Column(name = "bankorder")
    public String order_bank;

    public String description;

    public static Finder<Long, Transaction> find = new Finder<Long, Transaction>(Long.class, Transaction.class);

    public static List<Transaction> all() {
        return find.where().eq("status_id", Status.findByName("activated").id).findList();
    }


    public static List<Transaction> findByAccount(Long account) {
        return find.where().eq("account_id", account).findList();
    }


    public Transaction(Accounts account, Long amount, Transactiontype transactiontype, Transactionprocess transactionprocess, Long servicedeal, String description, Status status , Transactionmethod transactionmethod) {
        this.account = account;
        this.amount = amount;
        this.transactiontype = transactiontype;
        this.transactionprocess = transactionprocess;
        this.description = description;
    }

    public static void create(Accounts account, Long amount, Transactiontype transactiontype, Transactionprocess transactionprocess, Long servicedeal, String description, Status status , Transactionmethod transactionmethod) {
        Transaction transaction = new Transaction(account, amount, transactiontype, transactionprocess, servicedeal, description, status, transactionmethod);
        transaction.save();
    }

    public static void create(Transaction transaction) {
        transaction.save();
        Datelist datelist = new Datelist(Objecttype.findByName("transaction"), transaction.id, Datetype.findByName("create"), new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()), models.Status.findByName("activated"));
        Datelist.create(datelist);
    }

    public static void pay(Transaction transaction) {
        transaction.save();
        Datelist datelist = new Datelist(Objecttype.findByName("transaction"), transaction.id, Datetype.findByName("pay"), new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()), models.Status.findByName("activated"));
        Datelist.pay(datelist);
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public String getDateCreate(Long id) {
        return Datelist.getString(models.Objecttype.findByName("transaction"), id, models.Datetype.findByName("create"));
    }
    public String getDatePay(Long id) {
        return Datelist.getString(models.Objecttype.findByName("transaction"), id, models.Datetype.findByName("pay"));
    }
}
