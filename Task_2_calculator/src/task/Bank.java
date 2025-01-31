package task;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the annual interest rate (in %): ");
        double annualInterestRate = scanner.nextDouble();

        System.out.print("Enter the starting balance: ");
        double startingBalance = scanner.nextDouble();

        System.out.print("Enter the number of months: ");
        int numberOfMonths = scanner.nextInt();

        SavingAccount account = new SavingAccount(annualInterestRate, startingBalance);

        for (int month = 1; month <= numberOfMonths; month++) {
            System.out.printf("Enter the amount deposited in month %d: ", month);
            double depositAmount = scanner.nextDouble();
            account.deposit(depositAmount);

            System.out.printf("Enter the amount withdrawn in month %d: ", month);
            double withdrawAmount = scanner.nextDouble();
            account.withdraw(withdrawAmount);

            account.addMonthlyInterest();
        }

        System.out.printf("Ending balance: $ %.2f%n", account.getBalance());
        System.out.printf("Total deposits: $ %.2f%n", account.getTotalDeposits());
        System.out.printf("Total withdrawals: $ %.2f%n", account.getTotalWithdrawals());
        System.out.printf("Total interest earned: $ %.2f%n", account.getTotalInterestEarned());

        scanner.close();
    }
}
