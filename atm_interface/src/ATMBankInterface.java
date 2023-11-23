// Java Programming intern
// MicrOrbital Labs Internship Works Batch SIP2343
// Task 3 - ATM bank Interface
//Project code - MOLAP665
//Using Object oriented programming, develop a simple banking ATM account management system,
//that performs the transactions like deposit, withdrawl and balance enquiry.
// LinkedIn profile - https://www.linkedin.com/in/aditi-bengani/
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMBankInterface {
    private static final int ATM_PIN = 123;
    private static final Scanner scanner = new Scanner(System.in);

    public static class ATM {
        private double balance = 10000;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

    public interface AtmOperation {
        void execute();
    }

    public interface AtmOpInterface {
        void performOperation(AtmOperation operation);

        void viewBalance();

        void withdrawAmount(double amount);

        void depositAmount(double amount);

        void viewMiniStatement();
    }

    public static class ViewBalanceOperation implements AtmOperation {
        private final ATM atm;

        public ViewBalanceOperation(ATM atm) {
            this.atm = atm;
        }

        @Override
        public void execute() {
            System.out.println("\nLoading Account Balance.......");
            System.out.println("Your Current Balance is: " + atm.getBalance());
            System.out.println();
        }
    }

    public static class WithdrawOperation implements AtmOperation {
        private final ATM atm;
        private final Map<Double, String> miniStatement;

        public WithdrawOperation(ATM atm, Map<Double, String> miniStatement) {
            this.atm = atm;
            this.miniStatement = miniStatement;
        }

        @Override
        public void execute() {
            System.out.println("Enter amount to withdraw ");
            double withdrawAmount = scanner.nextDouble();

            if (isValidAmount(withdrawAmount) && withdrawAmount <= atm.getBalance() && confirmTransaction()) {
                miniStatement.put(withdrawAmount, " Amount Withdrawn");
                System.out.println("Collect the Cash " + withdrawAmount);
                atm.setBalance(atm.getBalance() - withdrawAmount);
                new ViewBalanceOperation(atm).execute();
            } else {
                System.out.println("Invalid withdrawal request.");
            }
        }

        private boolean isValidAmount(double amount) {
            return amount % 100 == 0;
        }

        private boolean confirmTransaction() {
            System.out.println("Confirm? Y/N");
            return scanner.next().equalsIgnoreCase("Y");
        }
    }

    public static class DepositOperation implements AtmOperation {
        private final ATM atm;
        private final Map<Double, String> miniStatement;

        public DepositOperation(ATM atm, Map<Double, String> miniStatement) {
            this.atm = atm;
            this.miniStatement = miniStatement;
        }
        private boolean confirmTransaction() {
            System.out.println("Confirm? Y/N");
            return scanner.next().equalsIgnoreCase("Y");
        }

        @Override
        public void execute() {
            System.out.println("Enter Amount to Deposit:");
            double depositAmount = scanner.nextDouble();

            if (confirmTransaction()) {
                miniStatement.put(depositAmount, " Amount Deposited");
                System.out.println(depositAmount + " Deposited Successfully!!");
                atm.setBalance(atm.getBalance() + depositAmount);
                new ViewBalanceOperation(atm).execute();
            }
        }
    }

    public static class MiniStatementOperation implements AtmOperation {
        private final Map<Double, String> miniStatement;

        public MiniStatementOperation(Map<Double, String> miniStatement) {
            this.miniStatement = miniStatement;
        }

        @Override
        public void execute() {
            for (Map.Entry<Double, String> entry : miniStatement.entrySet()) {
                System.out.println(entry.getKey() + entry.getValue());
            }
        }
    }

    public static class AtmOperations implements AtmOpInterface {
        private final ATM atm = new ATM();
        private final Map<Double, String> miniStatement = new HashMap<>();

        @Override
        public void performOperation(AtmOperation operation) {
            operation.execute();
        }

        @Override
        public void viewBalance() {
            performOperation(new ViewBalanceOperation(atm));
        }

        @Override
        public void withdrawAmount(double amount) {
            performOperation(new WithdrawOperation(atm, miniStatement));
        }

        @Override
        public void depositAmount(double amount) {
            performOperation(new DepositOperation(atm, miniStatement));
        }

        @Override
        public void viewMiniStatement() {
            performOperation(new MiniStatementOperation(miniStatement));
        }
    }

    public static void main(String[] args) {
        AtmOpInterface op = new AtmOperations();

        System.out.println("Welcome to your bank account !!!");

        while (true) {
            displayMenu();
            System.out.print("Enter Choice: ");
            int ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    op.viewBalance();
                    break;
                case 2:
                    processOption(op, "Withdraw Amount");
                    break;
                case 3:
                    processOption(op, "Deposit Amount");
                    break;
                case 4:
                    processOption(op, "View Ministatement");
                    break;
                case 5:
                    System.out.println("Collect your ATM Card\n Thank you for using ATM Machine!!");
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid choice");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n1. View Available Balance\n2. Withdraw Amount\n3. Deposit Amount\n4. View Ministatement\n5. Exit\n");
    }

    private static void processOption(AtmOpInterface op, String message) {
        System.out.print("Enter ATM Pin: ");
        int pin = scanner.nextInt();

        if (ATM_PIN == pin) {
            System.out.println("Account Authorized!\n");
            if ("Withdraw Amount".equals(message)) {
                op.withdrawAmount(0);  // Amount is entered within the WithdrawOperation
            } else if ("Deposit Amount".equals(message)) {
                op.depositAmount(0);  // Amount is entered within the DepositOperation
            } else {
                op.viewMiniStatement();
            }
        } else {
            System.out.println("Incorrect ATM pin");
            System.exit(0);
        }
    }
}
