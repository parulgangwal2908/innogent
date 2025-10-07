
#include <bits/stdc++.h>
using namespace std;

// Base Loan class (Abstraction)
class Loan {
protected:
    double principal;   
    double rate;        
    int years;          
public:
    Loan(double p, double r, int y) : principal(p), rate(r), years(y) {}
    virtual double calculateInterest() const = 0;
    virtual ~Loan() = default;
};

// Car Loan (Open for extension)
class CarLoan : public Loan {
public:
    CarLoan(double p, double r, int y) : Loan(p, r, y) {}

    double calculateInterest() const override {
        return (principal * rate * years) / 100.0;
    }
};

// Home Loan (Open for extension)
class HomeLoan : public Loan {
public:
    HomeLoan(double p, double r, int y) : Loan(p, r, y) {}

    double calculateInterest() const override {
        double amount = principal * pow((1 + rate / 100.0), years);
        return amount - principal;
    }
};

// Bank Service (DIP - works with abstraction)
class BankService {
private:
    Loan* loan;  
public:
    BankService(Loan* l) : loan(l) {}

    void showInterest() {
        cout << "Interest Payable: " << loan->calculateInterest() << endl;
    }
};


int main() {
    CarLoan car(500000, 8, 5);   
    HomeLoan home(2000000, 7, 10); 
    BankService carLoanService(&car);
    BankService homeLoanService(&home);
    cout <<"Car Loan";
    carLoanService.showInterest();

    cout <<"Home Loan";
    homeLoanService.showInterest();

    return 0;
}
