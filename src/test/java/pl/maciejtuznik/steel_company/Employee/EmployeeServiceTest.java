package pl.maciejtuznik.steel_company.Employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllEmployee() {
        employeeService.findAllEmployee();
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.findEmployeeById(1);
        assertTrue(foundEmployee.isPresent());
        assertEquals(1, foundEmployee.get().getId());
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertNotNull(savedEmployee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testPartiallyUpdateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setName("John");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("UpdatedName");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Optional<Employee> result = employeeService.partiallyUpdateEmployee(1, updatedEmployee);
        assertTrue(result.isPresent());
        assertEquals("UpdatedName", result.get().getName());
        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    public void testUpdateEmployee() {
        Employee existingEmployee = new Employee();
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("New Name");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Optional<Employee> result = employeeService.updateEmployee(1, updatedEmployee);
        assertTrue(result.isPresent());
        assertEquals("New Name", result.get().getName());
        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeRepository.existsById(1)).thenReturn(true);

        boolean result = employeeService.deleteEmployee(1);
        assertTrue(result);
        verify(employeeRepository, times(1)).existsById(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteEmployee_NotFound() {
        when(employeeRepository.existsById(1)).thenReturn(false);

        boolean result = employeeService.deleteEmployee(1);
        assertFalse(result);
        verify(employeeRepository, times(1)).existsById(1);
        verify(employeeRepository, never()).deleteById(1);
    }
}

