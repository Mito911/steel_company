package pl.maciejtuznik.steel_company.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CustromerController {

    private final CustomerService customerService;
    @Autowired
    public CustromerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customer-order")
    public ResponseEntity<Iterable<Customer>> findAllCustomer(){
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerInfo>> getAllCustomersOrders() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable Integer id){
       return customerService.findCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }


    @PostMapping("/customer")
    public ResponseEntity<Customer> addcustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        return  ResponseEntity.created(location).body(customer);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer){
     return  customerService.updateCustomer(id,customer)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PatchMapping("customer/{id}")
    public ResponseEntity<Customer> partiallyUpdateCustomer(@PathVariable Integer id, @RequestBody Customer customer){
        return customerService.partiallyUpdateCustomer(id,customer)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id){
        boolean deleted = customerService.deleteCustomer(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }






}
