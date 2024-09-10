package pl.maciejtuznik.steel_company.CustomerOrder;

import java.math.BigDecimal;

public class CustomerOrderInfo {

    private Integer quantity;

    private BigDecimal price;

    private String material;

    private String description;

    public CustomerOrderInfo(Integer quantity, BigDecimal price, String material, String description) {
        this.quantity = quantity;
        this.price = price;
        this.material = material;
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


