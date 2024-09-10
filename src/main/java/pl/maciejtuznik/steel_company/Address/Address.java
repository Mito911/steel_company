package pl.maciejtuznik.steel_company.Address;

import jakarta.persistence.*;
import pl.maciejtuznik.steel_company.Customer.Customer;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "delivery_address", length = 255)
    private String deliveryAddress;

    @Column(name = "correspondemce_address", length = 255)
    private String correspondemceAddress;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;


    public Address() {
    }


    public Address(Integer id, String deliveryAddress, String correspondemceAddress, Customer customer) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.correspondemceAddress = correspondemceAddress;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCorrespondemceAddress() {
        return correspondemceAddress;
    }

    public void setCorrespondemceAddress(String correspondemceAddress) {
        this.correspondemceAddress = correspondemceAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
