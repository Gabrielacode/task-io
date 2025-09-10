package com.garbi.taskio.exceptions;

public class TaskNotFoundException extends TaskException {
    public TaskNotFoundException() {
        super("Task Not Found");
    }
}
