package pl.maciejtuznik.steel_company.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

   private CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerInfo> findAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(customer -> new CustomerInfo(customer.getId(), customer.getName(), customer.getNip()))
                .collect(Collectors.toList());
    }
    public Iterable<Customer> findAllCustomer(){
      return   customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(Integer id){
       return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer){
      return  customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(Integer id, Customer customer){
     return    customerRepository.findById(id)
                .map(existingCustomer->{
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setNip(customer.getNip());

                    return customerRepository.save(existingCustomer);
                });
    }

    public Optional<Customer> partiallyUpdateCustomer(Integer id, Customer customer){
        return customerRepository.findById(id)
                .map(existingCustomer->{
                    if(customer.getName()!=null) existingCustomer.setName(customer.getName());
                    if(customer.getNip()!=null) existingCustomer.setNip(customer.getNip());

                    return customerRepository.save(existingCustomer);
                });
    }

    public boolean deleteCustomer(Integer id){
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
