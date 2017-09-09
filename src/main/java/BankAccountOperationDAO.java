import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BankAccountOperationDAO {
    public static BankAccountOperation getById(Integer id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        BankAccountOperation op = session.get(BankAccountOperation.class, id);
        session.close();
        return op;
    }

    public static List<BankAccountOperation> getByAccountId(Integer accountId) {
        //TODO
        return null;
    }

    public static void saveOperation(BankAccountOperation op) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.save(op);
        tr.commit();
        session.close();
    }
}
