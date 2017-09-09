import java.util.Date;
import java.util.List;

public class BankAccountOperationService {

    public static BankAccountOperation makeDeposit(BankAccountOperation accountOperation, Integer amount) {
        BankAccountOperation newOperation = new BankAccountOperation();
        newOperation.setAccountId(accountOperation.getAccountId());
        newOperation.setOperationAmount(amount);
        newOperation.setOperationDate(new Date());
        newOperation.setBalance(accountOperation.getBalance() + amount);
        BankAccountOperationDAO.saveOperation(newOperation);
        return newOperation;
    }

    public static BankAccountOperation makeWithdrawal(BankAccountOperation accountOperation, Integer amount) {
        BankAccountOperation newOperation = new BankAccountOperation();
        newOperation.setAccountId(accountOperation.getAccountId());
        newOperation.setOperationAmount(-amount);
        newOperation.setOperationDate(new Date());
        newOperation.setBalance(accountOperation.getBalance() - amount);
        BankAccountOperationDAO.saveOperation(newOperation);
        return newOperation;
    }

    public static String getStatementAsString(BankAccountOperation accountOperation) {
        StringBuilder sb = new StringBuilder();
        sb.append("Account id: ");
        sb.append(accountOperation.getAccountId());
        sb.append("\t | date: ");
        sb.append(accountOperation.getOperationDate());
        sb.append("\t | amount: ");
        sb.append(accountOperation.getOperationAmount());
        sb.append("\t | balance: ");
        sb.append(accountOperation.getBalance());
        return sb.toString();
    }

    public static String getHistoryAsString(Integer accountId) {
        StringBuilder sb = new StringBuilder();
        List<BankAccountOperation> results = BankAccountOperationDAO.getByAccountId(accountId);
        for (BankAccountOperation op : results) {
            sb.append(getStatementAsString(op));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
