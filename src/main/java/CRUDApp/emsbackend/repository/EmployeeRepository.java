package CRUDApp.emsbackend.repository;

import CRUDApp.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@EnableJpaRepositories
@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findOneByEmailAndPassword(String email, String password);

    Employee findByEmail(String email);
}
