package pl.maciejtuznik.steel_company.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public ResponseEntity<AddressInfo> createAddress(@RequestBody Address address, @RequestParam Integer customerId) {
        AddressInfo savedAddress = addressService.saveAddressWithCustomer(address, customerId);
        return ResponseEntity.ok(savedAddress);
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressInfo>> getAllAddresses() {
        return ResponseEntity.ok(addressService.findAllAddresses());
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Optional<AddressInfo>> getAddressById(@PathVariable Integer id) {
        Optional<AddressInfo> addressInfo = addressService.findAddressById(id);
        return addressInfo.isPresent() ? ResponseEntity.ok(addressInfo) : ResponseEntity.notFound().build();
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<AddressInfo> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        return addressService.updateAddress(id, address)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        boolean deleted = addressService.deleteAddress(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
