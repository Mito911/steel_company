package pl.maciejtuznik.steel_company.CustomerOrder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerOrderServiceTest {

    @Mock
    private CustomerOrderRepository customerOrderRepository;

    @InjectMocks
    private CustomerOrderService customerOrderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllOrder() {
        List<CustomerOrderInfo> orderInfoList = Arrays.asList(
                new CustomerOrderInfo(5, BigDecimal.valueOf(100), "C70U", "Wtorek"),
                new CustomerOrderInfo(10, BigDecimal.valueOf(200), "S335", "Na rano")
        );


        when(customerOrderRepository.findAllCustomerOrder()).thenReturn(orderInfoList);


        List<CustomerOrderInfo> result = customerOrderService.findAllOrder();


        assertEquals(2, result.size());
        verify(customerOrderRepository, times(1)).findAllCustomerOrder();
    }

    @Test
    public void testFindOrderById() {

        List<CustomerOrderInfo> orderInfoList = Arrays.asList(
                new CustomerOrderInfo(5, BigDecimal.valueOf(100), "S235", "Tlen+Azot")
        );


        when(customerOrderRepository.findCustomerOrderById(1)).thenReturn(orderInfoList);


        List<CustomerOrderInfo> result = customerOrderService.findOrderById(1);


        assertEquals(1, result.size());
        assertEquals("Steel", result.get(0).getMaterial());
        verify(customerOrderRepository, times(1)).findCustomerOrderById(1);
    }

    @Test
    public void testSaveOrder() {

        CustomerOrder order = new CustomerOrder();
        order.setQuantity(5);
        order.setPrice(BigDecimal.valueOf(100));
        order.setMaterial("Steel");
        order.setDescription("Test order");


        when(customerOrderRepository.save(order)).thenReturn(order);


        CustomerOrder result = customerOrderService.saveOrder(order);


        assertNotNull(result);
        assertEquals("Steel", result.getMaterial());
        verify(customerOrderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateOrder() {

        CustomerOrder existingOrder = new CustomerOrder();
        existingOrder.setId(1);
        existingOrder.setQuantity(5);
        existingOrder.setPrice(BigDecimal.valueOf(100));
        existingOrder.setMaterial("s235");
        existingOrder.setDescription("Azot");

        CustomerOrder newOrder = new CustomerOrder();
        newOrder.setQuantity(10);
        newOrder.setPrice(BigDecimal.valueOf(200));
        newOrder.setMaterial("Aluminium");
        newOrder.setDescription("Na juz ");


        when(customerOrderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
        when(customerOrderRepository.save(existingOrder)).thenReturn(existingOrder);


        Optional<CustomerOrder> result = customerOrderService.updateOrder(1, newOrder);


        assertTrue(result.isPresent());
        assertEquals(10, result.get().getQuantity());
        assertEquals("Aluminium", result.get().getMaterial());
        verify(customerOrderRepository, times(1)).findById(1);
        verify(customerOrderRepository, times(1)).save(existingOrder);
    }

    @Test
    public void testDeleteOrder() {

        when(customerOrderRepository.existsById(1)).thenReturn(true);


        boolean result = customerOrderService.deletedOrder(1);


        assertTrue(result);
        verify(customerOrderRepository, times(1)).existsById(1);
        verify(customerOrderRepository, times(1)).deleteCustomerOrderByID(1);
    }

    @Test
    public void testDeleteOrder_NotFound() {

        when(customerOrderRepository.existsById(1)).thenReturn(false);

        boolean result = customerOrderService.deletedOrder(1);


        assertFalse(result);
        verify(customerOrderRepository, times(1)).existsById(1);
        verify(customerOrderRepository, never()).deleteCustomerOrderByID(1);
    }
}

