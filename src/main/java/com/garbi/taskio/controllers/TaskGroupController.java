package com.garbi.taskio.controllers;

import com.garbi.taskio.dto.taskgroup.TaskGroupRequestDto;
import com.garbi.taskio.dto.taskgroup.TaskGroupResponseDto;
import com.garbi.taskio.services.TaskGroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is the controller class for the task group
@RestController
@RequestMapping("/task-group")
@AllArgsConstructor
public class TaskGroupController {
    private final TaskGroupService taskGroupService;

    //The first method is the Get which will get all the task groups

    @GetMapping
    public List<TaskGroupResponseDto> getAllTaskGroups(){
        return  taskGroupService.getAllTasks();
    }

    //This method will create a new task in the db
    @PostMapping
    public TaskGroupResponseDto createNewTaskGroup(@RequestBody TaskGroupRequestDto dto){
        return  taskGroupService.createNewTaskGroup(dto);
    }

    //This method will update a task group in the db based on the id passed in the parameter
    @PutMapping("/{id}")
    public TaskGroupResponseDto updateANewTaskGroup( @RequestBody TaskGroupRequestDto dto,@PathVariable("id")Integer id){
        return  taskGroupService.updateTaskGroup(dto,id);
    }

    //This method will delete a task group

    @DeleteMapping("/{id}")
    public String deleteANewTaskGroup(@PathVariable("id")Integer id){
        taskGroupService.deleteTaskGroupById(id);
        return "Task deleted";
    }

}
