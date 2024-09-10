package pl.maciejtuznik.steel_company.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejtuznik.steel_company.Customer.Customer;
import pl.maciejtuznik.steel_company.Customer.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public AddressInfo saveAddressWithCustomer(Address address, Integer customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            address.setCustomer(customerOpt.get());
            Address savedAddress = addressRepository.save(address);
            return new AddressInfo(savedAddress.getId(), savedAddress.getDeliveryAddress(), savedAddress.getCorrespondemceAddress(), customerId);
        }
        return null;
    }

    public List<AddressInfo> findAllAddresses() {
        return ((List<Address>) addressRepository.findAll()).stream()
                .map(address -> new AddressInfo(address.getId(), address.getDeliveryAddress(), address.getCorrespondemceAddress(), address.getCustomer().getId()))
                .collect(Collectors.toList());
    }

    public Optional<AddressInfo> findAddressById(Integer id) {
        return addressRepository.findById(id)
                .map(address -> new AddressInfo(address.getId(), address.getDeliveryAddress(), address.getCorrespondemceAddress(), address.getCustomer().getId()));
    }

    public Optional<AddressInfo> updateAddress(Integer id, Address updatedAddress) {
        return addressRepository.findById(id)
                .map(existingAddress -> {
                    existingAddress.setDeliveryAddress(updatedAddress.getDeliveryAddress());
                    existingAddress.setCorrespondemceAddress(updatedAddress.getCorrespondemceAddress());
                    existingAddress.setCustomer(updatedAddress.getCustomer());
                    Address savedAddress = addressRepository.save(existingAddress);
                    return new AddressInfo(savedAddress.getId(), savedAddress.getDeliveryAddress(), savedAddress.getCorrespondemceAddress(), savedAddress.getCustomer().getId());
                });
    }

    public boolean deleteAddress(Integer id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

