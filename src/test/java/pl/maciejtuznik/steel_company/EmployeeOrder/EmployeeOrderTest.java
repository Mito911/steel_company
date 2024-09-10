package pl.maciejtuznik.steel_company.EmployeeOrder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maciejtuznik.steel_company.Customer.Customer;
import pl.maciejtuznik.steel_company.Customer.CustomerRepository;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrder;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrderRepository;
import pl.maciejtuznik.steel_company.Employee.Employee;
import pl.maciejtuznik.steel_company.Employee.EmployeeRepository;
import pl.maciejtuznik.steel_company.Employee.EmployeeService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class EmployeeOrderTest {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
     private CustomerRepository customerRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private EmployeeOrderService employeeOrderService;

    @Autowired
    private EmployeeOrderRepository employeeOrderRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testSaveAndFindEmployeeOrder(){

        Customer customer = new Customer();
        customer.setName("Azbet");
        customer.setNip("1234567890");
        customerRepository.save(customer);

        Employee employee = new Employee();
        employee.setName("Ania");
        employee.setLastname("Cebrat");
        employee.setWorkplace("Handlowiec");
        employeeRepository.save(employee);


        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setQuantity(10);
        customerOrder.setPrice(BigDecimal.valueOf(10000));
        customerOrder.setMaterial("s235");
        customerOrder.setCustomer(customer);
        customerOrderRepository.save(customerOrder);


        EmployeeOrder employeeOrder = new EmployeeOrder();
        employeeOrder.setEmployee(employee);
        employeeOrder.setCustomerOrder(customerOrder);
        employeeOrderService.createEmployeeOrder(employee.getId(), customerOrder.getId());

        List<EmployeeOrder> employeeOrders = employeeOrderRepository.findAll();
        assertEquals(employee.getId(), employeeOrders.get(employeeOrders.size() - 1).getEmployee().getId());
        assertEquals(customerOrder.getId(), employeeOrders.get(employeeOrders.size() - 1).getCustomerOrder().getId());
    }







}
