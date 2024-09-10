package pl.maciejtuznik.steel_company.EmployeeMachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeMachineController {

    private EmployeeMachineService employeeMachineService;


    @Autowired
    public EmployeeMachineController(EmployeeMachineService employeeMachineService) {
        this.employeeMachineService = employeeMachineService;
    }

    @GetMapping("/employeeMachine")
    public ResponseEntity<List<EmployeeMachineInfo>> getAllEmployeeMachines() {
        List<EmployeeMachineInfo> allMachines = employeeMachineService.findAllEmployeeMachine();
        return ResponseEntity.ok(allMachines);
    }


    @GetMapping("/employeeMachine/{id}")
    public ResponseEntity<EmployeeMachineInfo> getEmployeeMachine(@PathVariable Integer id) {
        return employeeMachineService.findEmployeeMachine(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeMachine> createEmployeeMachine(@RequestParam Integer employeeId, @RequestParam Integer machineId) {
        EmployeeMachine employeeMachine = employeeMachineService.createEmployeeMachine(employeeId, machineId);
        return ResponseEntity.ok(employeeMachine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeMachine(@PathVariable Integer id) {
        boolean deleted = employeeMachineService.deleteEmployeeMachine(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


