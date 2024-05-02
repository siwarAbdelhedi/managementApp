package CRUDApp.emsbackend.controller;

import CRUDApp.emsbackend.dto.TaskDTO;
import CRUDApp.emsbackend.service.TaskService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Setter


public class TaskController {

    @Autowired
    private TaskService taskService;

    //Build ADD Task REST API
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
       TaskDTO savedTask =  taskService.createTask(taskDTO);
       return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    //Build Get Task REST API
    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") Long taskId) {
        TaskDTO taskDTO =  taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDTO);
    }

    //Get All Tasks
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> taskDTOList = taskService.getAllTasks();
        return ResponseEntity.ok(taskDTOList);
    }

    //Build Update Task Rest API
    @PutMapping("{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") Long taskId, @RequestBody TaskDTO taskDTO) {
        TaskDTO taskDto =  taskService.updateTask(taskId, taskDTO);
        return ResponseEntity.ok(taskDto);
    }

    //Build Delete Task Rest API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Task deleted", HttpStatus.OK);
    }
}

