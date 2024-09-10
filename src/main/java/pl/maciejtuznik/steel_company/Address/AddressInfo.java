package pl.maciejtuznik.steel_company.Address;

public class AddressInfo {

    private Integer id;
    private String deliveryAddress;
    private String correspondemceAddress;
    private Integer customerId;

    public AddressInfo(Integer id, String deliveryAddress, String correspondemceAddress, Integer customerId) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.correspondemceAddress = correspondemceAddress;
        this.customerId = customerId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}

