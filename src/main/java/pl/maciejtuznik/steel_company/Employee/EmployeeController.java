package pl.maciejtuznik.steel_company.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class EmployeeController {



    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employees")
    public ResponseEntity<Iterable<Employee>>getAllEmployee(){
        return ResponseEntity.ok(employeeService.findAllEmployee());

    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id){
        return employeeService.findEmployeeById(id)
                .map(employee -> ResponseEntity.ok(employee))
                .orElseGet(()->ResponseEntity.notFound().build());

    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addUser(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();


        return  ResponseEntity.created(location).body(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee){
      return employeeService.updateEmployee(id, employee)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<Employee> partiallyUpdateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
       return employeeService.partiallyUpdateEmployee(id, employee)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id){
        boolean deleted = employeeService.deleteEmployee(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }


}


