package com.garbi.taskio.dto.task;

//This dto will be used to create a new task
public record TaskCreateRequestDto(
       String name,
       String description

) {
}
