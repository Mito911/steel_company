package pl.maciejtuznik.steel_company.Address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.maciejtuznik.steel_company.Customer.Customer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAddress() {
        Address address = new Address();
        AddressInfo addressInfo = new AddressInfo(1, "Warszawa, Fabryczna 1", "Żurawia 44, Warszawa", 1);

        when(addressService.saveAddressWithCustomer(any(Address.class), eq(1))).thenReturn(addressInfo);

        ResponseEntity<AddressInfo> response = addressController.createAddress(address, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Warszawa, Fabryczna 1", response.getBody().getDeliveryAddress()); // poprawka na getDeliveryAddress()
        verify(addressService, times(1)).saveAddressWithCustomer(any(Address.class), eq(1));
    }

    @Test
    public void testGetAllAddresses() {
        List<AddressInfo> addresses = List.of(new AddressInfo(1, "Warszawa, Fabryczna 1", "Żurawia 44, Warszawa", 1));
        when(addressService.findAllAddresses()).thenReturn(addresses);

        ResponseEntity<List<AddressInfo>> response = addressController.getAllAddresses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(addressService, times(1)).findAllAddresses();
    }

    @Test
    public void testGetAddressById() {
        AddressInfo addressInfo = new AddressInfo(1, "Warszawa, Fabryczna 1", "Żurawia 44, Warszawa", 1);
        when(addressService.findAddressById(1)).thenReturn(Optional.of(addressInfo));

        ResponseEntity<Optional<AddressInfo>> response = addressController.getAddressById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isPresent());
        assertEquals("Warszawa, Fabryczna 1", response.getBody().get().getDeliveryAddress()); // poprawka na getDeliveryAddress()
        verify(addressService, times(1)).findAddressById(1);
    }

    @Test
    public void testUpdateAddress() {
        Address address = new Address();
        AddressInfo addressInfo = new AddressInfo(1, "Updated Address", "Żurawia 44, Warszawa", 1);

        when(addressService.updateAddress(eq(1), any(Address.class))).thenReturn(Optional.of(addressInfo));

        ResponseEntity<AddressInfo> response = addressController.updateAddress(1, address);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Address", response.getBody().getDeliveryAddress()); // poprawka na getDeliveryAddress()
        verify(addressService, times(1)).updateAddress(eq(1), any(Address.class));
    }

    @Test
    public void testDeleteAddress() {
        when(addressService.deleteAddress(1)).thenReturn(true);

        ResponseEntity<Void> response = addressController.deleteAddress(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(addressService, times(1)).deleteAddress(1);
    }

    @Test
    public void testDeleteAddress_NotFound() {
        when(addressService.deleteAddress(1)).thenReturn(false);

        ResponseEntity<Void> response = addressController.deleteAddress(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(addressService, times(1)).deleteAddress(1);
    }
}
