package com.garbi.taskio.dto.task;

import jakarta.validation.constraints.NotBlank;

//This dto will be used to create a new task
public record TaskCreateRequestDto(
        @NotBlank(message = "Task name should not be blank")
       String name,
        @NotBlank(message = "Task description should not be blank")
       String description

) {
}
