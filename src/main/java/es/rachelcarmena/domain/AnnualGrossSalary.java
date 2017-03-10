package es.rachelcarmena.domain;

public class AnnualGrossSalary extends Amount {

    public AnnualGrossSalary(int amount) {
        super(amount);
    }

    public AnnualGrossSalary(String amount) {
        super(amount);
    }

    public MonthlyGrossSalary toMonthlyGrossSalary() {
        return new MonthlyGrossSalary(this.perMonth().value);
    }
}