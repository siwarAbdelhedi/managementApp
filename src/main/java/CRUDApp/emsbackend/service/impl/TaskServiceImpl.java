package CRUDApp.emsbackend.service.impl;

import CRUDApp.emsbackend.dto.TaskDTO;
import CRUDApp.emsbackend.entity.Task;
import CRUDApp.emsbackend.exception.ResoourceNotFoundException;
import CRUDApp.emsbackend.mapper.TaskMapper;
import CRUDApp.emsbackend.repository.TaskRepo;
import CRUDApp.emsbackend.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {


    private TaskRepo taskRepo;


    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {

        Task task = TaskMapper.mapTaskDTOToTask(taskDTO);
        Task savedTask = taskRepo.save(task);
        return TaskMapper.mapTaskToTaskDTO(savedTask);
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {

       Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResoourceNotFoundException("Task not found" +taskId));
        return TaskMapper.mapTaskToTaskDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream().map((task) -> TaskMapper.mapTaskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO updatedTask) {

        Task task = taskRepo.findById(taskId).orElseThrow(
                () -> new ResoourceNotFoundException("Task not found")

        );

        task.setNameTask(updatedTask.getNameTask());
        task.setDescriptionTask(updatedTask.getDescriptionTask());

         Task updatedTaskObj =  taskRepo.save(task);

        return TaskMapper.mapTaskToTaskDTO(updatedTaskObj);
    }

    @Override
    public void deleteTask(Long taskId) {

        Task task= taskRepo.findById(taskId).orElseThrow(
                () -> new ResoourceNotFoundException("Task not found" +taskId)
        );
        taskRepo.deleteById(taskId);
    }
}
