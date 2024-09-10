package pl.maciejtuznik.steel_company.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> findAllEmployee(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Integer id){
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> partiallyUpdateEmployee(Integer id, Employee employee){
        return employeeRepository.findById(id)
                .map(existingEmployee->{
                    if(employee.getName() != null) existingEmployee.setName(employee.getName());
                    if(employee.getLastname() != null) existingEmployee.setLastname(employee.getLastname());
                    if(employee.getWorkplace() != null) existingEmployee.setWorkplace(employee.getWorkplace());
                    if(employee.getDate_of_employment() != null) existingEmployee.setDate_of_employment(employee.getDate_of_employment());
                    return employeeRepository.save(existingEmployee);
                });
    }

    public Optional<Employee> updateEmployee(Integer id, Employee employee) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setName(employee.getName());
            existingEmployee.setLastname(employee.getLastname());
            existingEmployee.setWorkplace(employee.getWorkplace());
            existingEmployee.setDate_of_employment(employee.getDate_of_employment());
            return employeeRepository.save(existingEmployee);
        });
    }

    public boolean deleteEmployee(Integer id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
