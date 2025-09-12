package com.garbi.taskio.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//For updating tasks
public record TaskUpdateRequestDto(
        @NotBlank(message = "Task name should not be blank")
        String name,
        @NotBlank(message = "Task description should not be blank")
        String description,
        @NotNull(message = "IsCompleted is null please enter the boolean value ")
        boolean isCompleted
) {
}
