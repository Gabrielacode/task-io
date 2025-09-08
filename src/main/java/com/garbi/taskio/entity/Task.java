package com.garbi.taskio.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
//This is the entity class for a Task
//A Task  will contain ,
/// An id,  A name , description,timeCreated and whether it is completed
/// It will also be associated with a task group such that a Task group can contain many tasks

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//We use Lombok for Setters, Getters and Constructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;
    String description;
    //We will use Local Date and Time so as to be get time based on the server locale
    LocalDateTime createdTime;
    boolean isCompleted;
    @ManyToOne()
    @JoinColumn(name = "task_group_id")
    TaskGroup taskGroup;

}
