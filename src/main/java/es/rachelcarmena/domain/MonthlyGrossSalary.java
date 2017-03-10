package es.rachelcarmena.domain;

import java.math.BigDecimal;

public class MonthlyGrossSalary extends Amount {
    public MonthlyGrossSalary(int amount) {
        super(amount);
    }

    public MonthlyGrossSalary(BigDecimal amount) {
        super(amount);
    }

    public MonthlyGrossSalary(String amount) {
        super(amount);
    }
}