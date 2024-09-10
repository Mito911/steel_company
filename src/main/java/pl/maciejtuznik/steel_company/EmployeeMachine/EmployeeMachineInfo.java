package pl.maciejtuznik.steel_company.EmployeeMachine;

public class EmployeeMachineInfo {

    private String employeeName;
    private String employeeLastName;
    private String workplace;
    private String machineName;

    public EmployeeMachineInfo(String employeeName, String employeeLastName, String workplace,String machineName) {
        this.employeeName = employeeName;
        this.employeeLastName = employeeLastName;
        this.workplace=workplace;
        this.machineName = machineName;

    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }
    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }


}
