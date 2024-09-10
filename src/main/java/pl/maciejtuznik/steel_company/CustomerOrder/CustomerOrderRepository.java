package pl.maciejtuznik.steel_company.CustomerOrder;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Integer> {

    @Query("SELECT new pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrderInfo(c.quantity, c.price, c.material, c.description) " +
            "FROM CustomerOrder c")
    List<CustomerOrderInfo> findAllCustomerOrder();


    @Query("SELECT new pl.maciejtuznik.steel_company.CustomerOrder.CustomerOrderInfo(c.quantity, c.price, c.material, c.description) " +
            "FROM CustomerOrder c WHERE c.id = :id")
    List<CustomerOrderInfo> findCustomerOrderById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM CustomerOrder co WHERE co.id = :id")
    void deleteCustomerOrderByID(@Param("id") Integer id);




}
