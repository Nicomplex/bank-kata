import javax.persistence.NoResultException;
import java.util.Date;

public class Main {

    private static BankAccountOperation account = null;

    private static void printUsage() {
        System.out.println("Choose an action by entering the corresponding letter :");
        System.out.println("D -> Make a Deposit");
        System.out.println("W -> Make a Withdrawal");
        System.out.println("H -> Show History of operations");
    }

    private static int parseAmount() {
        return Integer.parseInt(System.console().readLine());
    }

    private static void makeAction(String input) {
        String s = input.toUpperCase();
        if (s.equals("D")) {
            System.out.println("Enter the amount of deposit: ");
            BankAccountOperationService.makeDeposit(account, parseAmount());
            System.out.println(BankAccountOperationService.getStatementAsString(BankAccountOperationDAO.getLastByAccountId(1)));
        } else if (s.equals("W")) {
            System.out.println("Enter the amount of withdrawal: ");
            BankAccountOperationService.makeWithdrawal(account, parseAmount());
            System.out.println(BankAccountOperationService.getStatementAsString(BankAccountOperationDAO.getLastByAccountId(1)));
        } else if (s.equals("H")) {
            System.out.println("History");
            System.out.println(BankAccountOperationService.getHistoryAsString(1));
        } else {
            System.out.println("Wrong Parameter !");
            printUsage();
        }
    }

    public static void main(String[] args) {
        try {
            account = BankAccountOperationDAO.getLastByAccountId(1);
        } catch (NoResultException e) {
            account = new BankAccountOperation();
            account.setAccountId(1);
            account.setOperationDate(new Date());
            account.setBalance(0);
            BankAccountOperationDAO.saveOperation(account);
        }
        while(true) {
            account = BankAccountOperationDAO.getLastByAccountId(1);
            printUsage();
            String input = System.console().readLine();
            makeAction(input);
        }
    }
}
