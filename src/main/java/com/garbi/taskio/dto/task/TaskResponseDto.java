package com.garbi.taskio.dto.task;

//This is the dto for a task response
public record TaskResponseDto (
        Integer id,
        String name,
        String description,
        String createdTime,
        String taskGroupId,
        String taskGroupName

) {
}
