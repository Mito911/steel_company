package pl.maciejtuznik.steel_company.Customer;

public class CustomerInfo {

    private Integer id;
    private String name;
    private String nip;

    public CustomerInfo(Integer id, String name, String nip) {
        this.id = id;
        this.name = name;
        this.nip = nip;
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
}
