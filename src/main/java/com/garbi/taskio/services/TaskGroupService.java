package com.garbi.taskio.services;

import com.garbi.taskio.dto.mappers.TaskGroupMapper;
import com.garbi.taskio.dto.mappers.TaskMapper;
import com.garbi.taskio.dto.task.TaskResponseDto;
import com.garbi.taskio.dto.taskgroup.TaskGroupRequestDto;
import com.garbi.taskio.dto.taskgroup.TaskGroupResponseDto;
import com.garbi.taskio.entity.Task;
import com.garbi.taskio.entity.TaskGroup;
import com.garbi.taskio.exceptions.TaskGroupNotFoundException;
import com.garbi.taskio.exceptions.TaskNotFoundException;
import com.garbi.taskio.repositories.TaskGroupRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

///  This service will handle all the operations that are carried
///  We will be able to carry out these activities in the task group
///  1. Create a New Task Group
///  2. Update a Task Group name, description , and priority
///  3. Delete a task group deleting all the tasks in it
///  4. Get a TaskGroups based on Id and All the task groups
@Service
public class TaskGroupService {
 private  final TaskGroupRepositoryImpl taskGroupRepository;

    public TaskGroupService(TaskGroupRepositoryImpl taskGroupRepository) {
        this.taskGroupRepository = taskGroupRepository;
    }

    public TaskGroupResponseDto createNewTaskGroup(TaskGroupRequestDto dto){

  //We will create a new Task Group object
  //Populate it and then save it to the db
   TaskGroup group = new TaskGroup();
   group = TaskGroupMapper.createNewTaskGroupFromRequestDto(dto);

   //Then we save it
      group = taskGroupRepository.save(group);

      return TaskGroupMapper.taskGroupToTaskGroupResponse(group);
 }

 public TaskGroupResponseDto updateTaskGroup(TaskGroupRequestDto dto, Integer id ) throws TaskGroupNotFoundException{
     //We will  update it by checking if the task group is there and then
     // We will then  update it
     Optional<TaskGroup> isTaskGroupInDbResult = taskGroupRepository.findById(id);
     var taskGroup = isTaskGroupInDbResult.orElseThrow(TaskGroupNotFoundException::new);
     var updatedTaskGroup =  TaskGroupMapper.updateTaskFromTaskUpdateRequestDto(dto, taskGroup);
     updatedTaskGroup = taskGroupRepository.save(updatedTaskGroup);
     return  TaskGroupMapper.taskGroupToTaskGroupResponse(updatedTaskGroup);

 }
 //Then  the delete operation

    public void deleteTaskGroupById(Integer id) throws TaskGroupNotFoundException{
        if (!taskGroupRepository.existsById(id))  throw new TaskGroupNotFoundException();
        taskGroupRepository.deleteById(id);
    }
    //This will get all the list of the Task Groups in the db
    public List<TaskGroupResponseDto> getAllTasksGroups(){
        return  taskGroupRepository.findAll().stream().map(TaskGroupMapper::taskGroupToTaskGroupResponse).collect(Collectors.toList());
    }
    public TaskGroupResponseDto getTaskGroupById(Integer Id) throws TaskGroupNotFoundException{
        var taskGroupResult = taskGroupRepository.findById(Id);
        var taskGroup = taskGroupResult.orElseThrow(TaskGroupNotFoundException::new);
        return TaskGroupMapper.taskGroupToTaskGroupResponse(taskGroup);
    }


}
