package pl.maciejtuznik.steel_company.EmployeeMachine;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maciejtuznik.steel_company.Employee.Employee;
import pl.maciejtuznik.steel_company.Employee.EmployeeRepository;
import pl.maciejtuznik.steel_company.Machine.Machine;
import pl.maciejtuznik.steel_company.Machine.MachineRepository;

import java.util.List;

@SpringBootTest
@Transactional
public class EmployeeMachineTest {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMachineService employeeMachineService;

    @Autowired
    private EmployeeMachineRepository employeeMachineRepository;



    @Test
    public void testSaveAndFindEmployeeMachine(){
        Employee employee = new Employee();
        employee.setName("Krzysztof");
        employee.setLastname("Rozpara");
        employee.setWorkplace("Operator lasera");
        employeeRepository.save(employee);

        Machine machine = new Machine();
        machine.setName("Triumph");
        machine.setQuantity(1);
        machineRepository.save(machine);

        EmployeeMachine employeeMachine = new EmployeeMachine();
        employeeMachine.setEmployee(employee);
        employeeMachine.setMachine(machine);
        employeeMachineService.createEmployeeMachine(employee.getId(),machine.getId());

        List<EmployeeMachine> allEmployeeMachine = employeeMachineRepository.findAll();
        Assertions.assertEquals(employee.getId(),allEmployeeMachine.get(allEmployeeMachine.size()-1).getEmployee().getId());
        Assertions.assertEquals(machine.getId(),allEmployeeMachine.get(allEmployeeMachine.size()-1).getMachine().getId());

    }
}
