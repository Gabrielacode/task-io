package com.garbi.taskio.dto.taskgroup;

import com.garbi.taskio.constants.TaskGroupPriority;

public record TaskGroupRequestDto (
        Integer id,
        String name,
        String description,
        TaskGroupPriority priority
) {
}
