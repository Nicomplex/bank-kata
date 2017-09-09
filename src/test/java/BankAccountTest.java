import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
    private SessionFactory sessionFactory = null;
    private Session session = null;

    @Before
    public void init() {
        this.sessionFactory = HibernateSessionFactory.getSessionFactory();
        this.session = sessionFactory.openSession();
    }

    @After
    public void close() {
        this.session.close();
        this.sessionFactory.close();
    }

    @Test
    public void should_connect_to_database() {
        Session testSession = this.session;
        Assert.assertTrue(testSession.isOpen());
    }
}
