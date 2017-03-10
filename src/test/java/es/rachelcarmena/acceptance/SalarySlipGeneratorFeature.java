package es.rachelcarmena.acceptance;

import es.rachelcarmena.domain.Employee;
import es.rachelcarmena.SalarySlipGenerator;
import es.rachelcarmena.delivery.Console;
import es.rachelcarmena.calculator.NationalInsuranceContributionCalculator;
import es.rachelcarmena.calculator.TaxesCalculator;
import es.rachelcarmena.domain.Amount;
import es.rachelcarmena.domain.AnnualGrossSalary;
import es.rachelcarmena.domain.MonthlyGrossSalary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SalarySlipGeneratorFeature {

    private final AnnualGrossSalary ANNUAL_GROSS_SALARY = new AnnualGrossSalary(24000);
    private final MonthlyGrossSalary MONTHLY_GROSS_SALARY = new MonthlyGrossSalary(2000);
    private final Amount NATIONAL_INSURANCE_CONTRIBUTION = new Amount("159.40");
    private final Amount TAX_FREE_ALLOWANCE = new Amount("916.67");
    private final Amount TAXABLE_INCOME = new Amount("1083.33");
    private final Amount TAX_PAYABLE = new Amount("216.67");

    private final Employee employee = new Employee(12345, "John J Doe", ANNUAL_GROSS_SALARY);

    @Mock Console console;

    @Test
    public void should_print_a_salary_slip_with_employee_details_for_an_employee() {
        NationalInsuranceContributionCalculator nationalInsuranceContributionCalculator = new NationalInsuranceContributionCalculator();
        TaxesCalculator taxesCalculator = new TaxesCalculator();
        SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator(console, nationalInsuranceContributionCalculator, taxesCalculator);
        salarySlipGenerator.generateFor(employee);

        verify(console).printLine("Employee ID: 12345");
        verify(console).printLine("Employee Name: John J Doe");
        verify(console).printLine("Gross Salary: £2000.00");
        verify(console).printLine("National Insurance contributions: £159.40");
        verify(console).printLine("Tax-free allowance: £916.67");
        verify(console).printLine("Taxable income: £1083.33");
        verify(console).printLine("Tax payable: £216.67");
    }
}
