package pl.maciejtuznik.steel_company.EmployeeMachine;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maciejtuznik.steel_company.EmployeeOrder.EmployeeOrder;
import pl.maciejtuznik.steel_company.EmployeeOrder.EmployeeOrderRepository;

import java.util.List;

@SpringBootTest
@Transactional
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeMachineRepository employeeMachineRepository;

    @Test
    public void testFindEmployeeMachineInfo(){
        List<EmployeeMachineInfo> allEmployeeMachineInfo = employeeMachineRepository.findAllEmployeeMachineInfo();
        Assertions.assertNotNull(allEmployeeMachineInfo);

        EmployeeMachineInfo firstInfo = allEmployeeMachineInfo.get(0);
        Assertions.assertEquals("Sebastian", firstInfo.getEmployeeName());
    }

    @Test
    public void testFindEmployeeMachineInfoById(){
        List<EmployeeMachineInfo> employeeMachineInfoById = employeeMachineRepository.findEmployeeMachineInfoById(1);
        Assertions.assertNotNull(employeeMachineInfoById);

        Assertions.assertEquals(1,employeeMachineInfoById.size());
    }
    @Test
    public void testDeleteEmployeeMachineById(){
        employeeMachineRepository.deleteEmployeeMachineById(1);

        List<EmployeeMachineInfo> employeeMachineInfoById = employeeMachineRepository.findEmployeeMachineInfoById(1);
        Assertions.assertEquals(0,employeeMachineInfoById.size());
    }


}
