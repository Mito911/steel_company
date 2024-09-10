package pl.maciejtuznik.steel_company.EmployeeOrder;

import jakarta.persistence.*;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrder;
import pl.maciejtuznik.steel_company.Employee.Employee;

@Entity
public class EmployeeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_employee_id",referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="fk_order_id",referencedColumnName = "id")
    private CustomerOrder customerOrder;

    public EmployeeOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
