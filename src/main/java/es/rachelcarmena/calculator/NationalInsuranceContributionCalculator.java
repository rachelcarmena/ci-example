package es.rachelcarmena.calculator;

import es.rachelcarmena.calculator.common.LimitAndRateRelation;
import es.rachelcarmena.calculator.common.SalaryAttributeCalculator;
import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.AnnualGrossSalary;

import java.util.ArrayList;
import java.util.List;

public class NationalInsuranceContributionCalculator {

    public Amount calculate(AnnualGrossSalary annualGrossSalary) {
        List<LimitAndRateRelation> limitAndRateRelations = getLimitAndRateRelations();

        SalaryAttributeCalculator salaryAttributeCalculator = new SalaryAttributeCalculator(limitAndRateRelations);
        Amount attribute = salaryAttributeCalculator.calculate(annualGrossSalary);
        return attribute.perMonth();
    }

    private List<LimitAndRateRelation> getLimitAndRateRelations() {
        final Amount MAX_LIMIT_NO_CONTRIBUTIONS = new Amount("8060.00");
        final Amount MAX_LIMIT_BASIC_CONTRIBUTIONS = new Amount("43000.00");

        List<LimitAndRateRelation> limitAndRateRelations = new ArrayList<>();
        limitAndRateRelations.add(new LimitAndRateRelation(MAX_LIMIT_BASIC_CONTRIBUTIONS, 2));
        limitAndRateRelations.add(new LimitAndRateRelation(MAX_LIMIT_NO_CONTRIBUTIONS, 12));
        return limitAndRateRelations;
    }
}
