package pl.maciejtuznik.steel_company.Machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MachineControllerTest {

    @Mock
    private MachineService machineService;

    @InjectMocks
    private MachineController machineController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllMachine() {
        Iterable<Machine> machines = List.of(new Machine(), new Machine());
        when(machineService.findAllMachine()).thenReturn(machines);

        ResponseEntity<Iterable<Machine>> response = machineController.findAllMachine();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(machineService, times(1)).findAllMachine();
    }

    @Test
    public void testFindMachine() {
        Machine machine = new Machine();
        machine.setId(1);
        when(machineService.findMachine(1)).thenReturn(Optional.of(machine));

        ResponseEntity<Optional<Machine>> response = machineController.findMachine(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isPresent());
        verify(machineService, times(1)).findMachine(1);
    }



    @Test
    public void testUpdateMachine() {
        Machine machine = new Machine();
        when(machineService.updateMachine(eq(1), any(Machine.class))).thenReturn(Optional.of(machine));

        ResponseEntity<Machine> response = machineController.updateMachine(1, machine);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(machineService, times(1)).updateMachine(1, machine);
    }

    @Test
    public void testDeleteMachine() {
        when(machineService.deleteMachine(1)).thenReturn(true);

        ResponseEntity<Void> response = machineController.deleteMachine(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(machineService, times(1)).deleteMachine(1);
    }

    @Test
    public void testDeleteMachine_NotFound() {
        when(machineService.deleteMachine(1)).thenReturn(false);

        ResponseEntity<Void> response = machineController.deleteMachine(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(machineService, times(1)).deleteMachine(1);
    }
}

