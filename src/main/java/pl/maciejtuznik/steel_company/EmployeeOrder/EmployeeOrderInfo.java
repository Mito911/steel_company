package pl.maciejtuznik.steel_company.EmployeeOrder;


import java.math.BigDecimal;

public class EmployeeOrderInfo {

    String employeeName;
    String employeeLastName;
    String customerName;
    Integer quantity;
    String material;
    BigDecimal price;


    public EmployeeOrderInfo(String employeeName, String employeeLastName, String customerName, Integer quantity, String material, BigDecimal price) {
        this.employeeName = employeeName;
        this.employeeLastName = employeeLastName;
        this.customerName = customerName;
        this.quantity = quantity;
        this.material = material;
        this.price = price;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

