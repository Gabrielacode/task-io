package com.garbi.taskio.services;

import com.garbi.taskio.dto.mappers.TaskMapper;
import com.garbi.taskio.dto.task.TaskCreateRequestDto;
import com.garbi.taskio.dto.task.TaskResponseDto;
import com.garbi.taskio.dto.task.TaskUpdateRequestDto;
import com.garbi.taskio.entity.Task;
import com.garbi.taskio.entity.TaskGroup;
import com.garbi.taskio.exceptions.TaskGroupNotFound;
import com.garbi.taskio.exceptions.TaskNotFound;
import com.garbi.taskio.repositories.TaskGroupRepositoryImpl;
import com.garbi.taskio.repositories.TaskRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//This task service class will be in charge of handling all operations of a task
public class TaskService {
    private final TaskRepositoryImpl taskRepository;
    private final TaskGroupRepositoryImpl taskGroupRepository;

    public TaskService(TaskRepositoryImpl taskRepository, TaskGroupRepositoryImpl taskGroupRepository) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    //First creation of a task
    //A task can be created with a  by passing the name, description and the task group id  of a chosen task group
    //And it will return a TaskResponseDto

    public TaskResponseDto createNewTask(TaskCreateRequestDto taskCreateRequestDto,Integer taskGroupId) throws TaskGroupNotFound {
        //First we will need to get the task group to be associated with this task
         var taskGroupResult = taskGroupRepository.findById(taskGroupId);
         //There is no task group we will throw an exception that will be handled by the controller advice or exception handler
         TaskGroup taskGroup = taskGroupResult.orElseThrow(TaskGroupNotFound::new);
         //Then we will create a new task based on the information passed
          //We will use the mapper
        var task =  TaskMapper.createNewTaskFromTaskCreateRequestDto(taskCreateRequestDto,taskGroup);
        //Then save it
         var createdTask = taskRepository.save(task);
         //Then we convert it to a task Response dto
          return TaskMapper.taskToTaskResponse(createdTask);
    }

    public TaskResponseDto updateNewTask(TaskUpdateRequestDto taskUpdateRequestDto,Integer id) throws TaskNotFound{
        //We will get  the task request and then convert it to a task request dto
        // A task cannot change the task group once created
        //First we will check if it is in the db then if not we throw an exception
        //Then we update the object and save it which will automatically upsert it into the db
        Optional<Task> isTaskInDbResult = taskRepository.findById(id);
        var task = isTaskInDbResult.orElseThrow(TaskNotFound::new);
        var updatedTask = TaskMapper.updateTaskFromTaskUpdateRequestDto(taskUpdateRequestDto,task);
        //Then we update it
        updatedTask = taskRepository.save(updatedTask);
        //Then we convert it to a task Response dto
        return TaskMapper.taskToTaskResponse(updatedTask);

    }
    public void deleteTaskById(Integer id){
        //We will delete the task based on the  id of the task
        taskRepository.deleteById(id);
    }
    //Then we will read all the tasks
    //For now there will be no paging
    public List<TaskResponseDto> getAllTasks(){
        return  taskRepository.findAll().stream().map(TaskMapper::taskToTaskResponse).collect(Collectors.toList());
    }
}
