import java.util.List;

public class BankAccountOperationService {
    private BankAccountOperation accountOperation;

    public BankAccountOperationService(BankAccountOperation bankAccountOperation) {
        accountOperation = bankAccountOperation;
    }

    public void makeDeposit(Integer amount) {
        //TODO
    }

    public void makeWithdrawal(Integer amount) {
        //TODO
    }

    public static List<BankAccountOperation> getHistory(Integer accountId) {
        //TODO
        return null;
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
