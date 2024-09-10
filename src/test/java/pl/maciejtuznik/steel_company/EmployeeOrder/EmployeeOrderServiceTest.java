package pl.maciejtuznik.steel_company.EmployeeOrder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrder;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrderRepository;
import pl.maciejtuznik.steel_company.Employee.Employee;
import pl.maciejtuznik.steel_company.Employee.EmployeeRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class EmployeeOrderServiceTest {
    @Mock
    private EmployeeOrderRepository employeeOrderRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CustomerOrderRepository customerOrderRepository;

    @InjectMocks
    private EmployeeOrderService employeeOrderService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindEmployeeOrderById(){
        EmployeeOrderInfo orderinfo = new EmployeeOrderInfo("Wiktoria", "Ziemniak", "art-But", 20, "2mm s235", BigDecimal.valueOf(38000));
        when(employeeOrderRepository.findEmployeeOrderInfoById(1)).thenReturn(Arrays.asList(orderinfo));

        List<EmployeeOrderInfo> result =employeeOrderService.findEmployeeOrderById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1,result.size());

    }

    @Test
    public void testFindAllEmployeeOrder(){
        EmployeeOrderInfo orderinfo = new EmployeeOrderInfo("Wiktoria", "Ziemniak", "art-But", 20, "2mm s235", BigDecimal.valueOf(38000));
        when(employeeOrderRepository.findAllEmployeeOrder()).thenReturn(Arrays.asList(orderinfo));

        List<EmployeeOrderInfo> result = employeeOrderService.findAllEmployeeOrder();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1,result.size());
    }

    @Test
    public void testCreateEmployeeOrder(){
        Employee employee = new Employee();
        employee.setId(1);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(2);
        when(customerOrderRepository.findById(2)).thenReturn(Optional.of(customerOrder));

        employeeOrderService.createEmployeeOrder(1,2);

        verify(employeeRepository).findById(1);
        verify(customerOrderRepository).findById(2);

        ArgumentCaptor<EmployeeOrder> orderCaptor=ArgumentCaptor.forClass(EmployeeOrder.class);
        verify(employeeOrderRepository).save(orderCaptor.capture());

        EmployeeOrder saveOrder = orderCaptor.getValue();
        Assertions.assertNotNull(saveOrder);
        Assertions.assertEquals(1,saveOrder.getEmployee().getId());
        Assertions.assertEquals(2,saveOrder.getCustomerOrder().getId());

    }

    @Test
    public void testDeleteEmployeeOrder_RecordDoesNotExists(){
        Integer id=1;
        when(employeeRepository.existsById(id)).thenReturn(false);

        boolean result = employeeOrderService.deleteEmployeeOrder(id);

        Assertions.assertFalse(result);
        verify(employeeOrderRepository,never()).deleteEmployeeOrderByID(id);
    }

    @Test
    public void testDeleteEmployeeOrder_RecordExists(){
        Integer id=1;
        when(employeeOrderRepository.existsById(id)).thenReturn(true);

        boolean result=employeeOrderService.deleteEmployeeOrder(id);

        Assertions.assertTrue(result);
        verify(employeeOrderRepository,times(1)).deleteEmployeeOrderByID(id);

    }

}
