package pl.maciejtuznik.steel_company.CustomerOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
public class CustomerOrderController {
    private final CustomerOrderService orderService;
    @Autowired
    public CustomerOrderController(CustomerOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<CustomerOrderInfo>> findAllOrder(){
        return ResponseEntity.ok(orderService.findAllOrder());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<CustomerOrderInfo>> findOrder(@PathVariable Integer id){
        List<CustomerOrderInfo> orderById = orderService.findOrderById(id);

        if(orderById.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(orderById);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<CustomerOrder> addOrder(@RequestBody CustomerOrder order){
        orderService.saveOrder(order);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return  ResponseEntity.created(location).body(order);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<CustomerOrder> updateOrder(@PathVariable Integer id, @RequestBody CustomerOrder order){
        return  orderService.updateOrder(id,order)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PatchMapping("order/{id}")
    public ResponseEntity<CustomerOrder> partiallyUpdateOrder(@PathVariable Integer id, @RequestBody CustomerOrder order){
        return orderService.partiallyUpdateOrder(id,order)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        boolean deleted = orderService.deletedOrder(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }
}
