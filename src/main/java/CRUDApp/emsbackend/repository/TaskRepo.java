package CRUDApp.emsbackend.repository;

import CRUDApp.emsbackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
}