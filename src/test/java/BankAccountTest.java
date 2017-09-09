import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.List;

public class BankAccountTest {
    private static SessionFactory sessionFactory = null;

    @BeforeClass
    public static void initClass() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @AfterClass
    public static void closeClass() {
        sessionFactory.close();
    }

    @Test
    public void should_connect_to_database() {
        Session testSession = sessionFactory.openSession();
        Assert.assertTrue(testSession.isOpen());
        testSession.close();
    }

    @Test
    public void should_return_zero_operation() {
        List operationList = BankAccountOperationDAO.getByAccountId(1);
        Assert.assertTrue(operationList == null);
    }

    @Test
    public void should_create_one_operation() {
        BankAccountOperation op = new BankAccountOperation();
        op.setId(1);
        BankAccountOperationDAO.saveOperation(op);
        BankAccountOperation expected = BankAccountOperationDAO.getById(1);
        Assert.assertNotNull(expected);
    }
}
