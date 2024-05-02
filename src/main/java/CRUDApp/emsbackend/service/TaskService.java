package CRUDApp.emsbackend.service;

import CRUDApp.emsbackend.dto.EmployeeDto;
import CRUDApp.emsbackend.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO getTaskById(Long taskId);

    List<TaskDTO> getAllTasks();

    TaskDTO updateTask(Long taskId, TaskDTO updatedTask);

    void deleteTask(Long taskId);
}
