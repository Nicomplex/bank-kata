import java.util.Date;

public class BankAccountOperationService {
    private BankAccountOperation accountOperation;

    public BankAccountOperationService(BankAccountOperation bankAccountOperation) {
        accountOperation = bankAccountOperation;
    }

    public BankAccountOperation makeDeposit(Integer amount) {
        BankAccountOperation newOperation = new BankAccountOperation();
        newOperation.setAccountId(accountOperation.getAccountId());
        newOperation.setOperationAmount(amount);
        newOperation.setOperationDate(new Date());
        newOperation.setBalance(accountOperation.getBalance() + amount);
        BankAccountOperationDAO.saveOperation(newOperation);
        return newOperation;
    }

    public BankAccountOperation makeWithdrawal(Integer amount) {
        BankAccountOperation newOperation = new BankAccountOperation();
        newOperation.setAccountId(accountOperation.getAccountId());
        newOperation.setOperationAmount(amount);
        newOperation.setOperationDate(new Date());
        newOperation.setBalance(accountOperation.getBalance() - amount);
        BankAccountOperationDAO.saveOperation(newOperation);
        return newOperation;
    }

    public String getStatementAsString() {
        //TODO
        return null;
    }

    public static String getHistoryAsString(Integer accountId) {
        //TODO
        return null;
    }
}
