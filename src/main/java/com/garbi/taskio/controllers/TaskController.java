package com.garbi.taskio.controllers;

import com.garbi.taskio.dto.task.TaskCreateRequestDto;
import com.garbi.taskio.dto.task.TaskResponseDto;
import com.garbi.taskio.dto.task.TaskUpdateRequestDto;
import com.garbi.taskio.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor
public class TaskController {
    //This is the controller for the task
     private final TaskService taskService;
    //This method will get all the tasks in  the task group

    @GetMapping("/task-group/{group-id}/task")
    public List<TaskResponseDto> getAllTasks(@PathVariable("group-id") Integer groupId){
        return taskService.getAllTasks(groupId);
    }
    @GetMapping("/task-group/{group-id}/task/{task-id}")
    public TaskResponseDto getTaskById(
            @PathVariable("group-id") Integer groupId,
            @PathVariable("task-id") Integer taskId){
        return taskService.getTaskById(taskId,groupId);
    }

    //This method will create a new task and the task has to be under a task group

    @PostMapping("/task-group/{group-id}/task")
    public  TaskResponseDto createNewTaskUnderTaskGroup( @Valid @RequestBody TaskCreateRequestDto dto,
                                                         @PathVariable("group-id") Integer groupId){
        return  taskService.createNewTask(dto,groupId);
    }

    //This method will update  a task that is under the task group
    //We must ensure that the user cannot update a card if the card is not in the specified task group
    @PutMapping("/task-group/{group-id}/task/{task-id}")
    public TaskResponseDto updateATaskUnderATaskGroup(
            @Valid  @RequestBody TaskUpdateRequestDto dto,
            @PathVariable("group-id") Integer groupId,
            @PathVariable("task-id") Integer taskId
    ){
       return taskService.updateNewTask(dto,taskId,groupId);
    }
    //This will delete a task if the task belong to the task group and the task is found
    @DeleteMapping("/task-group/{group-id}/task/{task-id}")
    public String deleteATaskUnderATaskGroup(
            @PathVariable("group-id") Integer groupId,
            @PathVariable("task-id") Integer taskId
    ){
          taskService.deleteTaskById(taskId,groupId);
          return "Task "+taskId+"successfully deleted";
    }

}
