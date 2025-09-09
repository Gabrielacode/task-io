package com.garbi.taskio.dto.mappers;

import com.garbi.taskio.dto.task.TaskCreateRequestDto;
import com.garbi.taskio.dto.task.TaskResponseDto;
import com.garbi.taskio.dto.task.TaskUpdateRequestDto;
import com.garbi.taskio.dto.taskgroup.TaskGroupRequestDto;
import com.garbi.taskio.dto.taskgroup.TaskGroupResponseDto;
import com.garbi.taskio.entity.Task;
import com.garbi.taskio.entity.TaskGroup;

public class TaskGroupMapper {
    public static TaskGroup createNewTaskGroupFromRequestDto(TaskGroupRequestDto dto){
        TaskGroup  newTaskGroup = new TaskGroup();
        //Then we will set the values
        return updateTaskFromTaskUpdateRequestDto(dto,newTaskGroup);
    }

    public static TaskGroupResponseDto taskGroupToTaskGroupResponse(TaskGroup group){
        return  new TaskGroupResponseDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getPriority()
        );
    }
    public static TaskGroup updateTaskFromTaskUpdateRequestDto(TaskGroupRequestDto dto, TaskGroup group ){
        //Then we will set the values
        group.setName(dto.name());
        group.setDescription(dto.description());
        group.setPriority(dto.priority());
        return group;
    }
}
