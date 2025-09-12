package com.garbi.taskio.dto.taskgroup;

import com.garbi.taskio.constants.TaskGroupPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskGroupRequestDto (
        //We are adding validation to the requests so that the name and description are not blank
        @NotBlank(message = "Task Group name should not be blank")
        String name,
        @NotBlank(message = "Task Group description should not be blank")
        String description,
        @NotNull(message = "Task Group Priority should not be null ")
        TaskGroupPriority priority
) {
}
