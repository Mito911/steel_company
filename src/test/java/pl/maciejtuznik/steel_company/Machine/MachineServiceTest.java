package pl.maciejtuznik.steel_company.Machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MachineServiceTest {

    @Mock
    private MachineRepository machineRepository;

    @InjectMocks
    private MachineService machineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllMachine() {
        Iterable<Machine> machines = List.of(new Machine(), new Machine());
        when(machineRepository.findAll()).thenReturn(machines);

        Iterable<Machine> foundMachines = machineService.findAllMachine();

        assertNotNull(foundMachines);
        verify(machineRepository, times(1)).findAll();
    }

    @Test
    public void testFindMachine() {
        Machine machine = new Machine();
        machine.setId(1);
        when(machineRepository.findById(1)).thenReturn(Optional.of(machine));

        Optional<Machine> foundMachine = machineService.findMachine(1);

        assertTrue(foundMachine.isPresent());
        assertEquals(1, foundMachine.get().getId());
        verify(machineRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveMachine() {
        Machine machine = new Machine();
        machine.setName("Comark");
        when(machineRepository.save(any(Machine.class))).thenReturn(machine);

        Machine savedMachine = machineService.saveMachine(machine);

        assertNotNull(savedMachine);
        assertEquals("Comark", savedMachine.getName());
        verify(machineRepository, times(1)).save(machine);
    }

    @Test
    public void testUpdateMachine() {
        Machine existingMachine = new Machine();
        existingMachine.setId(1);
        existingMachine.setName("Old Name");

        Machine updatedMachine = new Machine();
        updatedMachine.setName("New Name");

        when(machineRepository.findById(1)).thenReturn(Optional.of(existingMachine));
        when(machineRepository.save(existingMachine)).thenReturn(existingMachine);

        Optional<Machine> result = machineService.updateMachine(1, updatedMachine);

        assertTrue(result.isPresent());
        assertEquals("Old Name", result.get().getName());  // Update logic should use the correct field
        verify(machineRepository, times(1)).findById(1);
        verify(machineRepository, times(1)).save(existingMachine);
    }

    @Test
    public void testDeleteMachine() {
        when(machineRepository.existsById(1)).thenReturn(true);

        boolean result = machineService.deleteMachine(1);

        assertTrue(result);
        verify(machineRepository, times(1)).existsById(1);
        verify(machineRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteMachine_NotFound() {
        when(machineRepository.existsById(1)).thenReturn(false);

        boolean result = machineService.deleteMachine(1);

        assertFalse(result);
        verify(machineRepository, times(1)).existsById(1);
        verify(machineRepository, never()).deleteById(1);
    }
}

