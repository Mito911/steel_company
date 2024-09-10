package pl.maciejtuznik.steel_company.EmployeeOrder;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeOrderRepository extends JpaRepository<EmployeeOrder,Integer> {


    @Query("SELECT new pl.maciejtuznik.steel_company.EmployeeOrder.EmployeeOrderInfo(e.employee.name, e.employee.lastName, e.customerOrder.customer.name, e.customerOrder.quantity, e.customerOrder.material, e.customerOrder.price) " +
            "FROM EmployeeOrder e WHERE e.employee.id = :id")
    List<EmployeeOrderInfo> findEmployeeOrderInfoById(@Param("id") Integer id);


    @Query("SELECT new pl.maciejtuznik.steel_company.EmployeeOrder.EmployeeOrderInfo(e.employee.name, e.employee.lastName, e.customerOrder.customer.name, e.customerOrder.quantity, e.customerOrder.material, e.customerOrder.price) " +
            "FROM EmployeeOrder e")
    List<EmployeeOrderInfo> findAllEmployeeOrder();

    @Query("SELECT e.employee.name, COUNT(e) as orderCount " +
            "FROM EmployeeOrder e " +
            "GROUP BY e.employee.id, e.employee.name " +
            "ORDER BY orderCount DESC")
    List<Object[]> findEmployeeOrderCounts();

    @Transactional
    @Modifying
    @Query("DELETE FROM EmployeeOrder em WHERE em.id = :id")
    void deleteEmployeeOrderByID(@Param("id") Integer id);


}
