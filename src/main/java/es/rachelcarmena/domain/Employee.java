package es.rachelcarmena.domain;

public class Employee {

    private final int employeeID;
    private final String employeeName;
    private final AnnualGrossSalary annualGrossSalary;

    public Employee(int employeeID, String employeeName, AnnualGrossSalary annualGrossSalary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.annualGrossSalary = annualGrossSalary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public AnnualGrossSalary getAnnualGrossSalary() {
        return annualGrossSalary;
    }
}
