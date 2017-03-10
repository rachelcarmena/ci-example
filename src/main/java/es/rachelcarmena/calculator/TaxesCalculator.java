package es.rachelcarmena.calculator;

import es.rachelcarmena.calculator.common.LimitAndRateRelation;
import es.rachelcarmena.calculator.common.SalaryAttributeCalculator;
import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.AnnualGrossSalary;
import es.rachelcarmena.domain.MonthlyGrossSalary;

import java.util.ArrayList;
import java.util.List;

public class TaxesCalculator {

    public Amount calculateFreeAllowance(MonthlyGrossSalary monthlyGrossSalary) {
        final Amount MAX_LIMIT_FREE_BAND = Amount.valueOf(11000);

        Amount freeBandPerMonth = MAX_LIMIT_FREE_BAND.perMonth();
        return freeBandPerMonth.min(monthlyGrossSalary);
    }

    public Amount calculateTaxableIncome(MonthlyGrossSalary monthlyGrossSalary, Amount taxFreeAllowance) {
        return monthlyGrossSalary.subtract(taxFreeAllowance);
    }

    public Amount calculateTaxPayable(AnnualGrossSalary annualGrossSalary) {
        List<LimitAndRateRelation> limitAndRateRelations = getLimitAndRateRelations(annualGrossSalary);

        SalaryAttributeCalculator salaryAttributeCalculator = new SalaryAttributeCalculator(limitAndRateRelations);
        Amount taxPayableFromAnnualSalary = salaryAttributeCalculator.calculate(annualGrossSalary);
        return taxPayableFromAnnualSalary.perMonth();
    }

    private List<LimitAndRateRelation> getLimitAndRateRelations(AnnualGrossSalary annualGrossSalary) {
        final Amount MAX_LIMIT_RULES_CHANGE = Amount.valueOf(100000);
        Amount maxLimitHighRate = Amount.valueOf(43000);
        Amount maxLimitBasicRate = Amount.valueOf(11000);

        Amount excessToChangeRules = annualGrossSalary.subtract(MAX_LIMIT_RULES_CHANGE);
        boolean hasExcessToChangeRules = excessToChangeRules.greaterThanZero();
        if (hasExcessToChangeRules) {
            Amount reduction = excessToChangeRules.divide(Amount.valueOf(2));
            maxLimitBasicRate = maxLimitBasicRate.subtract(reduction);
            maxLimitHighRate = maxLimitHighRate.subtract(reduction);
        }

        List<LimitAndRateRelation> limitAndRateRelations = new ArrayList<>();
        limitAndRateRelations.add(new LimitAndRateRelation(maxLimitHighRate, 40));
        limitAndRateRelations.add(new LimitAndRateRelation(maxLimitBasicRate, 20));
        limitAndRateRelations.add(new LimitAndRateRelation(Amount.valueOf(0), 0));
        return limitAndRateRelations;
    }
}