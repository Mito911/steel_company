package pl.maciejtuznik.steel_company.CustomerOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerOrderService {
    private CustomerOrderRepository orderRepository;
    @Autowired
    public CustomerOrderService(CustomerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    public List<CustomerOrderInfo> findAllOrder(){
        return  orderRepository.findAllCustomerOrder();
    }

    public List<CustomerOrderInfo> findOrderById(Integer id){
        return orderRepository.findCustomerOrderById(id);
    }

    public CustomerOrder saveOrder(CustomerOrder order){
        return  orderRepository.save(order);
    }

    public Optional<CustomerOrder> updateOrder(Integer id, CustomerOrder order){
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setQuantity(order.getQuantity());
                    existingOrder.setPrice(order.getPrice());
                    existingOrder.setMaterial(order.getMaterial());
                    existingOrder.setDescription(order.getDescription());
                    return orderRepository.save(existingOrder);
                });
    }

    public Optional<CustomerOrder> partiallyUpdateOrder(Integer id, CustomerOrder order){
        return orderRepository.findById(id)
                .map(existingOrder->{
                    if (order.getQuantity() != null) existingOrder.setQuantity(order.getQuantity());
                    if (order.getPrice() != null) existingOrder.setPrice(order.getPrice());
                    if (order.getMaterial() != null) existingOrder.setMaterial(order.getMaterial());
                    if (order.getDescription() != null) existingOrder.setDescription(order.getDescription());
                    return orderRepository.save(existingOrder);

                });
    }

    public boolean deletedOrder(Integer id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteCustomerOrderByID(id);
            return true;
        }
        else {
            return false;
        }
    }
}
