package com.garbi.taskio.dto.mappers;

import com.garbi.taskio.dto.task.TaskCreateRequestDto;
import com.garbi.taskio.dto.task.TaskResponseDto;
import com.garbi.taskio.dto.task.TaskUpdateRequestDto;
import com.garbi.taskio.entity.Task;
import com.garbi.taskio.entity.TaskGroup;

public final class TaskMapper {
    /// We want to map  from a task request and a task group to a task
    public static Task createNewTaskFromTaskCreateRequestDto(TaskCreateRequestDto dto, TaskGroup group){
        Task newTask = new Task();
        //Then we will set the values
        newTask.setName(dto.name());
        newTask.setDescription(dto.description());
        newTask.setTaskGroup(group);
        return newTask;
    }

    public static TaskResponseDto taskToTaskResponse(Task task){
        return  new TaskResponseDto(
                task.getId(),task.getName(),task.getDescription(),task.getCreatedTime().toString(), task.isCompleted(),task.getTaskGroup().getId().toString(),task.getTaskGroup().getName());
    }
    public static Task updateTaskFromTaskUpdateRequestDto(TaskUpdateRequestDto dto, Task task){
        //Then we will set the values
        task.setName(dto.name());
        task.setDescription(dto.description());
        task.setCompleted(dto.isCompleted());
        return task;
    }
}
