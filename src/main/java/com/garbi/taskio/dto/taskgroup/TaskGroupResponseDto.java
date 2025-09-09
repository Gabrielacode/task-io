package com.garbi.taskio.dto.taskgroup;

import com.garbi.taskio.constants.TaskGroupPriority;

public  record TaskGroupResponseDto(
        Integer id,
        String name,
        String description,
        TaskGroupPriority priority
) {
}
