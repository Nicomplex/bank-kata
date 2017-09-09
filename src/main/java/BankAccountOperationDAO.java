import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BankAccountOperationDAO {
    public static List<BankAccountOperation> getByAccountId(Integer accountId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createNamedQuery("getByAccountId", BankAccountOperation.class);
        query.setParameter("id", accountId);
        List results = query.getResultList();
        session.close();
        return results;
    }

    public static BankAccountOperation getLastByAccountId(Integer accountId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createNamedQuery("getLastByAccountId", BankAccountOperation.class);
        query.setParameter("id", accountId);
        query.setMaxResults(1);
        BankAccountOperation result = (BankAccountOperation) query.getSingleResult();
        session.close();
        return result;
    }

    public static void saveOperation(BankAccountOperation op) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.save(op);
        tr.commit();
        session.close();
    }
}
