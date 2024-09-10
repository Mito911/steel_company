package pl.maciejtuznik.steel_company.EmployeeMachine;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeMachineRepository extends JpaRepository<EmployeeMachine,Integer> {

    @Query("SELECT new pl.maciejtuznik.steel_company.EmployeeMachine.EmployeeMachineInfo(e.employee.name, e.employee.lastName, e.employee.workplace, e.machine.name) " +
            "FROM EmployeeMachine e")
    List<EmployeeMachineInfo> findAllEmployeeMachineInfo();

    @Query("SELECT new pl.maciejtuznik.steel_company.EmployeeMachine.EmployeeMachineInfo(e.employee.name, e.employee.lastName, e.employee.workplace, e.machine.name) " +
            "FROM EmployeeMachine e WHERE e.id = :id")
    List<EmployeeMachineInfo> findEmployeeMachineInfoById(@Param("id") Integer id);


    @Transactional
    @Modifying
    @Query("DELETE FROM EmployeeMachine em WHERE em.id = :id")
    boolean deleteEmployeeMachineById(@Param("id") Integer id);
}


