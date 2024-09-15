package task;

public class SavingAccount {
    private double annualInterestRate;
    private double balance;
    private double totalDeposits;
    private double totalWithdrawals;
    private double totalInterestEarned;

    public SavingAccount(double annualInterestRate, double startingBalance) {
        this.annualInterestRate = annualInterestRate;
        this.balance = startingBalance;
        this.totalDeposits = 0;
        this.totalWithdrawals = 0;
        this.totalInterestEarned = 0;
    }

    public void deposit(double amount) {
        balance += amount;
        totalDeposits += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            totalWithdrawals += amount;
        } else {
            System.out.println("Insufficient Amount withdrawal.");
        }
    }

    public void addMonthlyInterest() {
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        double interest = balance * monthlyInterestRate;
        balance += interest;
        totalInterestEarned += interest;
    }

    public double getBalance() {
        return balance;
    }

    public double getTotalDeposits() {
        return totalDeposits;
    }

    public double getTotalWithdrawals() {
        return totalWithdrawals;
    }

    public double getTotalInterestEarned() {
        return totalInterestEarned;
    }
}
