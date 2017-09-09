import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.*;

import java.util.Date;
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

    @Before
    public void truncateData() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createNativeQuery("TRUNCATE TABLE BANKACCOUNTOPERATION;");
        query.executeUpdate();
        tr.commit();
        session.close();
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
        Assert.assertTrue(operationList == null || operationList.size() == 0);
    }

    @Test
    public void should_create_one_operation() {
        BankAccountOperation op = new BankAccountOperation();
        op.setAccountId(1);
        BankAccountOperationDAO.saveOperation(op);
        List expected = BankAccountOperationDAO.getByAccountId(1);
        Assert.assertNotNull(expected);
    }

    @Test
    public void should_return_the_right_balance_and_date() {
        BankAccountOperation op = new BankAccountOperation();
        Date today = new Date();
        op.setAccountId(2);
        op.setOperationDate(today);
        op.setBalance(4242);
        BankAccountOperationDAO.saveOperation(op);
        BankAccountOperation expected = BankAccountOperationDAO.getLastByAccountId(2);
        Assert.assertEquals(4242, (long)expected.getBalance());
        Assert.assertEquals(today, expected.getOperationDate());
    }


    @Test
    public void should_return_the_last_balance_and_date() {
        BankAccountOperation op = new BankAccountOperation();
        BankAccountOperation op2 = new BankAccountOperation();
        Date today = new Date();
        op.setOperationDate(today);
        op.setAccountId(8);
        op.setBalance(500);
        op2.setOperationDate(new Date(today.getTime() - 24*60*60*1000));
        op2.setAccountId(8);
        op2.setBalance(200);
        BankAccountOperationDAO.saveOperation(op);
        BankAccountOperationDAO.saveOperation(op2);
        BankAccountOperation expected = BankAccountOperationDAO.getLastByAccountId(8);
        Assert.assertEquals(500, (long)expected.getBalance());
    }

    @Test
    public void should_return_operation_history() {
        BankAccountOperation op = new BankAccountOperation();
        op.setAccountId(42);
        BankAccountOperation op2 = new BankAccountOperation();
        op2.setAccountId(42);
        BankAccountOperationDAO.saveOperation(op);
        BankAccountOperationDAO.saveOperation(op2);
        List expected = BankAccountOperationDAO.getByAccountId(42);
        Assert.assertEquals(2, expected.size());
    }

    @Test
    public void should_update_operation_after_deposit() {
        BankAccountOperation op = new BankAccountOperation();
        op.setBalance(0);
        BankAccountOperationService bas = new BankAccountOperationService(op);
        BankAccountOperation expected = bas.makeDeposit(250);
        Assert.assertEquals(250, (long)expected.getBalance());
        Assert.assertEquals(250, (long)expected.getOperationAmount());
    }


    @Test
    public void should_update_operation_after_withdrawal() {
        BankAccountOperation op = new BankAccountOperation();
        op.setBalance(500);
        BankAccountOperationService bas = new BankAccountOperationService(op);
        BankAccountOperation expected = bas.makeWithdrawal(400);
        Assert.assertEquals(100, (long)expected.getBalance());
        Assert.assertEquals(400, (long)expected.getOperationAmount());
    }

    @Test
    public void should_add_an_operation_after_deposit() {
        BankAccountOperation op = new BankAccountOperation();
        op.setAccountId(9);
        op.setBalance(0);
        BankAccountOperationDAO.saveOperation(op);
        BankAccountOperationService bas = new BankAccountOperationService(op);
        bas.makeDeposit(250);
        List expected = BankAccountOperationDAO.getByAccountId(9);
        Assert.assertEquals(2, expected.size());
    }

    @Test
    public void should_add_an_operation_after_withdrawal() {
        BankAccountOperation op = new BankAccountOperation();
        op.setAccountId(10);
        op.setBalance(500);
        BankAccountOperationDAO.saveOperation(op);
        BankAccountOperationService bas = new BankAccountOperationService(op);
        bas.makeWithdrawal(250);
        List expected = BankAccountOperationDAO.getByAccountId(10);
        Assert.assertEquals(2, expected.size());
    }
}
