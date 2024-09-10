package pl.maciejtuznik.steel_company.EmployeeMachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.maciejtuznik.steel_company.Employee.Employee;
import pl.maciejtuznik.steel_company.Employee.EmployeeRepository;
import pl.maciejtuznik.steel_company.Machine.Machine;
import pl.maciejtuznik.steel_company.Machine.MachineRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class EmployeeMachineServiceTest {
    @Mock
    private EmployeeMachineRepository employeeMachineRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private MachineRepository machineRepository;

    @InjectMocks
    private EmployeeMachineService employeeMachineService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployeeMachine(){
        Employee employee = new Employee();
        employee.setId(2);
        when(employeeRepository.findById(2)).thenReturn(Optional.of(employee));

        Machine machine = new Machine();
        machine.setId(1);
        when(machineRepository.findById(1)).thenReturn(Optional.of(machine));

        employeeMachineService.createEmployeeMachine(2,1);

        verify(employeeRepository).findById(2);
        verify(machineRepository).findById(1);

        ArgumentCaptor<EmployeeMachine> employeeMachineArgumentCaptor = ArgumentCaptor.forClass(EmployeeMachine.class);
        verify(employeeMachineRepository).save(employeeMachineArgumentCaptor.capture());

        EmployeeMachine value = employeeMachineArgumentCaptor.getValue();
        Assertions.assertNotNull(value);
        Assertions.assertEquals(2,value.getEmployee().getId());
        Assertions.assertEquals(1,value.getMachine().getId());
    }
    @Test
    public void testDeleteEmployeeMachine() {
        Integer id = 1;
        when(employeeMachineRepository.existsById(id)).thenReturn(false);


        boolean result = employeeMachineService.deleteEmployeeMachine(id);
        Assertions.assertFalse(result);
        verify(employeeMachineRepository, never()).deleteEmployeeMachineById(id);
    }
}
