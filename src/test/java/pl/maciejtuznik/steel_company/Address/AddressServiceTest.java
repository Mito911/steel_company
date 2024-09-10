package pl.maciejtuznik.steel_company.Address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.maciejtuznik.steel_company.Customer.Customer;
import pl.maciejtuznik.steel_company.Customer.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAddressWithCustomer() {
        Address address = new Address();
        address.setDeliveryAddress("Warszawa, Fabryczna 1");
        address.setCorrespondemceAddress("Żurawia 44, Warszawa");

        Customer customer = new Customer();
        customer.setId(1);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressInfo savedAddress = addressService.saveAddressWithCustomer(address, 1);

        assertNotNull(savedAddress);
        assertEquals("Warszawa, Fabryczna 1", savedAddress.getDeliveryAddress());
        verify(customerRepository, times(1)).findById(1);
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    public void testFindAllAddresses() {
        Address address1 = new Address();
        address1.setId(1);
        address1.setDeliveryAddress("Warszawa, Fabryczna 1");
        address1.setCorrespondemceAddress("Żurawia 44, Warszawa");
        Customer customer = new Customer();
        customer.setId(1);
        address1.setCustomer(customer);

        Address address2 = new Address();
        address2.setId(2);
        address2.setDeliveryAddress("Kraków, Rzemieślnicza 10");
        address2.setCorrespondemceAddress("Kraków, Wesoła 12");
        address2.setCustomer(customer);

        when(addressRepository.findAll()).thenReturn(List.of(address1, address2));

        List<AddressInfo> addresses = addressService.findAllAddresses();

        assertEquals(2, addresses.size());
        assertEquals("Warszawa, Fabryczna 1", addresses.get(0).getDeliveryAddress());
        assertEquals("Kraków, Rzemieślnicza 10", addresses.get(1).getDeliveryAddress());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    public void testFindAddressById() {
        Address address = new Address();
        address.setId(1);
        address.setDeliveryAddress("Warszawa, Fabryczna 1");
        address.setCorrespondemceAddress("Żurawia 44, Warszawa");
        Customer customer = new Customer();
        customer.setId(1);
        address.setCustomer(customer);

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        Optional<AddressInfo> foundAddress = addressService.findAddressById(1);

        assertTrue(foundAddress.isPresent());
        assertEquals("Warszawa, Fabryczna 1", foundAddress.get().getDeliveryAddress());
        verify(addressRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdateAddress() {
        Address existingAddress = new Address();
        existingAddress.setId(1);
        existingAddress.setDeliveryAddress("Warszawa, Fabryczna 1");
        existingAddress.setCorrespondemceAddress("Żurawia 44, Warszawa");
        Customer customer = new Customer();
        customer.setId(1);
        existingAddress.setCustomer(customer);

        Address updatedAddress = new Address();
        updatedAddress.setDeliveryAddress("New Delivery Address");

        when(addressRepository.findById(1)).thenReturn(Optional.of(existingAddress));
        when(addressRepository.save(existingAddress)).thenReturn(existingAddress);

        Optional<AddressInfo> result = addressService.updateAddress(1, updatedAddress);

        assertTrue(result.isPresent());
        assertEquals("New Delivery Address", result.get().getDeliveryAddress());
        verify(addressRepository, times(1)).findById(1);
        verify(addressRepository, times(1)).save(existingAddress);
    }

    @Test
    public void testDeleteAddress() {
        when(addressRepository.existsById(1)).thenReturn(true);

        boolean result = addressService.deleteAddress(1);

        assertTrue(result);
        verify(addressRepository, times(1)).existsById(1);
        verify(addressRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteAddress_NotFound() {
        when(addressRepository.existsById(1)).thenReturn(false);

        boolean result = addressService.deleteAddress(1);

        assertFalse(result);
        verify(addressRepository, times(1)).existsById(1);
        verify(addressRepository, never()).deleteById(1);
    }
}



