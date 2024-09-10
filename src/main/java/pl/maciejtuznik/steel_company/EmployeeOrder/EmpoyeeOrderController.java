package pl.maciejtuznik.steel_company.EmployeeOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpoyeeOrderController {

    private final EmployeeOrderService employeeOrderService;
    @Autowired
    public EmpoyeeOrderController(EmployeeOrderService employeeOrderService) {
        this.employeeOrderService = employeeOrderService;
    }

    @GetMapping("/employeeOrder")
    public ResponseEntity<List<EmployeeOrderInfo>> findAllEmployeeOrder(){
      return   ResponseEntity.ok(employeeOrderService.findAllEmployeeOrder());
    }
    @GetMapping("/employeeOrder/{id}")
    public ResponseEntity<List<EmployeeOrderInfo>> getEmployeeOrderById(@PathVariable Integer id) {
        List<EmployeeOrderInfo> employeeOrderInfos = employeeOrderService.findEmployeeOrderById(id);

        if (employeeOrderInfos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(employeeOrderInfos);
        }
    }

    @PostMapping("/employeeOrder")
    public ResponseEntity<EmployeeOrder> createEmployeeOrder(@RequestBody Integer employeeId, @RequestBody Integer customerOrderId){
        EmployeeOrder employeeOrder = employeeOrderService.createEmployeeOrder(employeeId, customerOrderId);
        return ResponseEntity.ok(employeeOrder);
    }

    @GetMapping("/orderCounts")
    public ResponseEntity<List<Object[]>> getEmployeeOrderCounts() {
        List<Object[]> orderCounts = employeeOrderService.getEmployeeOrderCounts();
        return ResponseEntity.ok(orderCounts);
    }

    @DeleteMapping("/employeeOrder/{id}")
    public ResponseEntity<Void> deleteEmployeeOrder(@PathVariable Integer id){
        boolean deleted = employeeOrderService.deleteEmployeeOrder(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
