import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.List;

public class BankAccountTest {
    private static SessionFactory sessionFactory = null;
    private Session session = null;

    @BeforeClass
    public static void initClass() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Before
    public void init() {
        this.session = sessionFactory.openSession();
    }

    @AfterClass
    public static void closeClass() {
        sessionFactory.close();
    }

    @After
    public void close() {
        this.session.close();
    }

    @Test
    public void should_connect_to_database() {
        Session testSession = this.session;
        Assert.assertTrue(testSession.isOpen());
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
