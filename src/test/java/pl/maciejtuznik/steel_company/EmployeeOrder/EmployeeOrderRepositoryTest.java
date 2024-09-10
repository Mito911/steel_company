package pl.maciejtuznik.steel_company.EmployeeOrder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class EmployeeOrderRepositoryTest {
    @Autowired
    private EmployeeOrderRepository employeeOrderRepository;

    @Test
    public void testFindEmployeeOrderInfobyId(){
        List<EmployeeOrderInfo> employeeOrderInfoById = employeeOrderRepository.findEmployeeOrderInfoById(5);
        assertNotNull(employeeOrderInfoById);
        assertEquals(8,employeeOrderInfoById.size());
    }

    @Test
    public void testFindAllEmployeeOrder(){
        List<EmployeeOrderInfo> allEmployeeOrder = employeeOrderRepository.findAllEmployeeOrder();
        assertNotNull(allEmployeeOrder);
    }

    @Test
    public void testFindEmployeeOrderCounts(){
        List<Object[]> employeeOrderCounts = employeeOrderRepository.findEmployeeOrderCounts();
        assertNotNull(employeeOrderCounts);

        Object[] firstResult = employeeOrderCounts.get(0);
        String employeeName = (String) firstResult[0];
        assertEquals("Wiktoria", employeeName, "Nazwa pracownika powinna być 'Wiktoria'");
    }

    @Test
    public void testDeleteEmployeeOrderByID(){
        employeeOrderRepository.deleteEmployeeOrderByID(1);

        List<EmployeeOrderInfo> employeeOrderInfoById = employeeOrderRepository.findEmployeeOrderInfoById(1);
        assertEquals(0,employeeOrderInfoById.size());
    }

}
