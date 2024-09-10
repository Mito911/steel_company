package pl.maciejtuznik.steel_company.CustomerOrder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.maciejtuznik.steel_company.Customer.Customer;
import pl.maciejtuznik.steel_company.Customer.CustomerRepository;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Transactional
public class CustomerOrderTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Test
    public void SaveAndFindCustomerOrder() {

        Customer customer = new Customer();
        customer.setName("Mich-Stal");
        customer.setNip("4432526023");
        Customer savedCustomer = customerRepository.save(customer);
        Assertions.assertNotNull(savedCustomer);


        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(savedCustomer);
        customerOrder.setMaterial("0.5 mm S235");
        customerOrder.setPrice(BigDecimal.valueOf(3000));
        customerOrder.setDescription("tlen");
        customerOrder.setQuantity(100);


        CustomerOrder savedOrder = customerOrderRepository.save(customerOrder);
        Assertions.assertNotNull(savedOrder);
        Assertions.assertNotNull(savedOrder.getId());


        List<CustomerOrder> all = customerOrderRepository.findAll();
        CustomerOrder lastOrder = all.get(all.size() - 1);

        Assertions.assertEquals(savedCustomer.getId(), lastOrder.getCustomer().getId());
        Assertions.assertEquals(savedOrder.getId(), lastOrder.getId());
        Assertions.assertEquals(100, lastOrder.getQuantity());
    }
}

