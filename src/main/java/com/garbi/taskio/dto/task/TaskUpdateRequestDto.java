package com.garbi.taskio.dto.task;

//For updating tasks
public record TaskUpdateRequestDto(
        String name,
        String description,
        boolean isCompleted
) {
}
