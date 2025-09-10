package com.garbi.taskio.dto.taskgroup;

import com.garbi.taskio.constants.TaskGroupPriority;

public record TaskGroupRequestDto (

        String name,
        String description,
        TaskGroupPriority priority
) {
}
