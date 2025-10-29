import java.lang.Math;

// Base Loan class (Abstraction)
abstract class Loan {
    protected double principal;
    protected double rate;
    protected int years;

    public Loan(double principal, double rate, int years) {
        this.principal = principal;
        this.rate = rate;
        this.years = years;
    }

    // Abstract method
    public abstract double calculateInterest();
}

// Car Loan (Open for extension)
class CarLoan extends Loan {
    public CarLoan(double principal, double rate, int years) {
        super(principal, rate, years);
    }

    @Override
    public double calculateInterest() {
        return (principal * rate * years) / 100.0;
    }
}

// Home Loan (Open for extension)
class HomeLoan extends Loan {
    public HomeLoan(double principal, double rate, int years) {
        super(principal, rate, years);
    }

    @Override
    public double calculateInterest() {
        double amount = principal * Math.pow((1 + rate / 100.0), years);
        return amount - principal;
    }
}

// Bank Service (DIP - works with abstraction)
class BankService {
    private Loan loan;

    public BankService(Loan loan) {
        this.loan = loan;
    }

    public void showInterest() {
        System.out.println("Interest Payable: " + loan.calculateInterest());
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        CarLoan car = new CarLoan(500000, 8, 5);
        HomeLoan home = new HomeLoan(2000000, 7, 10);

        BankService carLoanService = new BankService(car);
        BankService homeLoanService = new BankService(home);

        System.out.println("Car Loan");
        carLoanService.showInterest();

        System.out.println("Home Loan");
        homeLoanService.showInterest();
    }
}
