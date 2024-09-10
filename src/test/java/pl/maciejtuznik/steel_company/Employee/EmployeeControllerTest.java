package pl.maciejtuznik.steel_company.Employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        employeeController.getAllEmployee();
        verify(employeeService, times(1)).findAllEmployee();
    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        when(employeeService.findEmployeeById(1)).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> response = employeeController.getEmployee(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(employeeService, times(1)).findEmployeeById(1);
    }

    @Test
    public void testGetEmployee_NotFound() {
        when(employeeService.findEmployeeById(1)).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = employeeController.getEmployee(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeService, times(1)).findEmployeeById(1);
    }


    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        when(employeeService.updateEmployee(eq(1), any(Employee.class))).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> response = employeeController.updateEmployee(1, employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).updateEmployee(1, employee);
    }



    @Test
    public void testPartiallyUpdateEmployee() {
        Employee employee = new Employee();
        when(employeeService.partiallyUpdateEmployee(eq(1), any(Employee.class))).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> response = employeeController.partiallyUpdateEmployee(1, employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).partiallyUpdateEmployee(1, employee);
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeService.deleteEmployee(1)).thenReturn(true);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1);
    }

    @Test
    public void testDeleteEmployee_NotFound() {
        when(employeeService.deleteEmployee(1)).thenReturn(false);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1);
    }
}

