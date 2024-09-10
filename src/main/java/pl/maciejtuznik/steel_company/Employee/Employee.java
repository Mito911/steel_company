package pl.maciejtuznik.steel_company.Employee;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @NotNull(message = "Pole 'name' nie może być puste")
    private String name;

    @Column(name = "last_name")
    @NotNull(message = "Pole 'name' nie może być puste")
    private String lastName;

    @Column(name = "workplace")
    private String workplace;

    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;

    public Employee() {
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

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String last_name) {
        this.lastName = last_name;
    }

    public LocalDate getDate_of_employment() {
        return dateOfEmployment;
    }

    public void setDate_of_employment(LocalDate date_of_employment) {
        this.dateOfEmployment = date_of_employment;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

}
