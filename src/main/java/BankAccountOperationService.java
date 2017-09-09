public class BankAccountOperationService {
    private BankAccountOperation accountOperation;

    public BankAccountOperationService(BankAccountOperation bankAccountOperation) {
        accountOperation = bankAccountOperation;
    }

    public BankAccountOperation makeDeposit(Integer amount) {
        //TODO
        return null;
    }

    public BankAccountOperation makeWithdrawal(Integer amount) {
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
