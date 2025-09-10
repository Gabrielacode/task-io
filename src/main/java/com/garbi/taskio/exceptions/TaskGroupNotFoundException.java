package com.garbi.taskio.exceptions;

public class TaskGroupNotFoundException extends TaskException {

    public TaskGroupNotFoundException() {
        super("Task Group Not Found");
    }
}
