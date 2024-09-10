package pl.maciejtuznik.steel_company.CustomerOrder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerOrderRepositoryTest {

    @Mock
    private CustomerOrderRepository customerOrderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicjalizacja mocków
    }

    @Test
    public void testFindAllCustomerOrder() {

        CustomerOrderInfo orderInfo = new CustomerOrderInfo(10, BigDecimal.valueOf(2000), "S235", "Tlen");
        when(customerOrderRepository.findAllCustomerOrder()).thenReturn(List.of(orderInfo));

        List<CustomerOrderInfo> result = customerOrderRepository.findAllCustomerOrder();

        assertEquals(1, result.size());
        assertEquals(orderInfo.getQuantity(), result.get(0).getQuantity());
        assertEquals(orderInfo.getPrice(), result.get(0).getPrice());
        assertEquals(orderInfo.getMaterial(), result.get(0).getMaterial());
        assertEquals(orderInfo.getDescription(), result.get(0).getDescription());

        verify(customerOrderRepository, times(1)).findAllCustomerOrder();
    }

    @Test
    public void testFindCustomerOrderById() {

        Integer id = 1;
        CustomerOrderInfo orderInfo = new CustomerOrderInfo(10, BigDecimal.valueOf(2000), "Steel", "Test description");
        when(customerOrderRepository.findCustomerOrderById(id)).thenReturn(List.of(orderInfo));


        List<CustomerOrderInfo> result = customerOrderRepository.findCustomerOrderById(id);

        assertEquals(1, result.size());
        assertEquals(orderInfo.getQuantity(), result.get(0).getQuantity());
        assertEquals(orderInfo.getPrice(), result.get(0).getPrice());
        assertEquals(orderInfo.getMaterial(), result.get(0).getMaterial());
        assertEquals(orderInfo.getDescription(), result.get(0).getDescription());


        verify(customerOrderRepository, times(1)).findCustomerOrderById(id);
    }

    @Test
    public void testDeleteCustomerOrderByID() {

        Integer id = 1;


        doNothing().when(customerOrderRepository).deleteCustomerOrderByID(id);


        customerOrderRepository.deleteCustomerOrderByID(id);
        verify(customerOrderRepository, times(1)).deleteCustomerOrderByID(id);
    }
}
