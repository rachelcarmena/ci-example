package es.rachelcarmena.unit;

import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.AnnualGrossSalary;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class NationalInsuranceContributionCalculatorShould {

    private NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator = new NationalInsuranceContributionCalculator();

    @Test
    public void calculate_national_insurance_contribution_for_no_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(5000);

        assertEquals(nationalInsuranceContributionCalculator.calculate(annualGrossSalary), new Amount(0));
    }

    @Test
    public void calculate_national_insurance_contribution_for_basic_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(9060);

        assertEquals(new Amount(10), nationalInsuranceContributionCalculator.calculate(annualGrossSalary));
    }

    @Test
    public void calculate_national_insurance_contribution_for_higher_contributions_and_personal_allowance_band() {
        AnnualGrossSalary annualGrossSalary = new AnnualGrossSalary(45000);

        assertEquals(new Amount("352.73"), nationalInsuranceContributionCalculator.calculate(annualGrossSalary));
    }
}
