package pl.maciejtuznik.steel_company.Customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import pl.maciejtuznik.steel_company.Address.Address;
import pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrder;

import java.util.List;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    @NotNull(message = "Pole 'name' nie może być puste")
    private String name;

    @Column(name="NIP")
    @Pattern(regexp = "\\d{10}", message = "NIP musi składać się z 10 cyfr")
    private String nip;

    @OneToMany(mappedBy = "customer")
    List<CustomerOrder> order;

    @OneToMany(mappedBy = "customer")
    List<Address> address;

    public Customer() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public List<CustomerOrder> getOrder() {
        return order;
    }

    public void setOrder(List<CustomerOrder> order) {
        this.order = order;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
