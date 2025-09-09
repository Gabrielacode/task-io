package com.garbi.taskio.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskGroupPriority {
    //When we want to deserialize it we will want the input to be  able touse but lowe case and upper case
    //SO we will add

    HIGH, MEDIUM, LOW;

}
