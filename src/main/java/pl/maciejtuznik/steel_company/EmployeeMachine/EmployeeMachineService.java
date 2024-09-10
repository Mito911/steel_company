package pl.maciejtuznik.steel_company.EmployeeMachine;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejtuznik.steel_company.Employee.Employee;
import pl.maciejtuznik.steel_company.Employee.EmployeeRepository;
import pl.maciejtuznik.steel_company.Machine.Machine;
import pl.maciejtuznik.steel_company.Machine.MachineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeMachineService {

    private final EmployeeMachineRepository employeeMachineRepository;


    private EmployeeRepository employeeRepository;


    private MachineRepository machineRepository;

    public EmployeeMachineService(EmployeeMachineRepository employeeMachineRepository, EmployeeRepository employeeRepository, MachineRepository machineRepository) {
        this.employeeMachineRepository = employeeMachineRepository;
        this.employeeRepository = employeeRepository;
        this.machineRepository = machineRepository;
    }

    @Autowired
    public List<EmployeeMachineInfo> findAllEmployeeMachine(){
        return employeeMachineRepository.findAllEmployeeMachineInfo();
    }

    public Optional<EmployeeMachineInfo> findEmployeeMachine(Integer id) {
        return employeeMachineRepository.findEmployeeMachineInfoById(id)
                .stream()
                .findFirst();
    }

    public EmployeeMachine createEmployeeMachine(Integer employeeId, Integer machineId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        Machine machine = machineRepository.findById(machineId).orElseThrow(() -> new RuntimeException("Machine not found"));

        EmployeeMachine employeeMachine = new EmployeeMachine();
        employeeMachine.setEmployee(employee);
        employeeMachine.setMachine(machine);

        return employeeMachineRepository.save(employeeMachine);
    }

    public boolean deleteEmployeeMachine(Integer id) {

        if(employeeMachineRepository.existsById(id)){
            employeeMachineRepository.deleteEmployeeMachineById(id);
            return true;
        }
        else {
            return false;
        }
    }
}


