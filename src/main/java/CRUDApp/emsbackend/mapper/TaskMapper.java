package CRUDApp.emsbackend.mapper;

import CRUDApp.emsbackend.dto.TaskDTO;
import CRUDApp.emsbackend.entity.Task;

public class TaskMapper {

    public static TaskDTO mapTaskToTaskDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getNameTask(),
                task.getDescriptionTask()
        );
    }

    public static Task mapTaskDTOToTask(TaskDTO taskDTO) {
        return new Task(
                taskDTO.getId(),
                taskDTO.getNameTask(),
                taskDTO.getDescriptionTask()
        );
    }
}
