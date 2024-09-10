package pl.maciejtuznik.steel_company.EmployeeOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrder;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrderRepository;
import pl.maciejtuznik.steel_company.Employee.Employee;
import pl.maciejtuznik.steel_company.Employee.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeOrderService {

    private final EmployeeOrderRepository employeeOrderRepository;
    private EmployeeRepository employeeRepository;

    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    public EmployeeOrderService(EmployeeOrderRepository employeeOrderRepository, EmployeeRepository employeeRepository, CustomerOrderRepository customerOrderRepository) {
        this.employeeOrderRepository = employeeOrderRepository;
        this.employeeRepository = employeeRepository;
        this.customerOrderRepository = customerOrderRepository;
    }



    public List<EmployeeOrderInfo> findEmployeeOrderById(Integer id) {
        return employeeOrderRepository.findEmployeeOrderInfoById(id);
    }

    public List<EmployeeOrderInfo> findAllEmployeeOrder(){
      return   employeeOrderRepository.findAllEmployeeOrder();
    }

    public EmployeeOrder createEmployeeOrder(Integer employeeId, Integer customerOrderId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found"));
        CustomerOrder customerOrder= customerOrderRepository.findById(customerOrderId).orElseThrow(()->new RuntimeException("CustomerOrder not found"));

        EmployeeOrder employeeOrder= new EmployeeOrder();
        employeeOrder.setEmployee(employee);
        employeeOrder.setCustomerOrder(customerOrder);

        return employeeOrderRepository.save(employeeOrder);
    }

    public List<Object[]> getEmployeeOrderCounts() {
        return employeeOrderRepository.findEmployeeOrderCounts();
    }

    public boolean deleteEmployeeOrder(Integer id) {
        if (employeeOrderRepository.existsById(id)) {
            employeeOrderRepository.deleteEmployeeOrderByID(id);
            return true;
        } else {
            return false;
        }
    }

}
