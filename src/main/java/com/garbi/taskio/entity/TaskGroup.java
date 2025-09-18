package com.garbi.taskio.entity;

import com.garbi.taskio.constants.TaskGroupPriority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/// This is a Task Group entity
/// A Task Group will hold a list of Task items or entities and can be set with a name,description  and rating

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//We use Lombok for Setters, Getters and Constructor
public class TaskGroup {

    @Id
    @GeneratedValue
    Integer id;

    String name;
    String description;
    @CreationTimestamp
    LocalDateTime createdTime;
    TaskGroupPriority priority;



    //We want to cascade all operations to the children
    @OneToMany(mappedBy = "taskGroup",cascade = CascadeType.ALL)
    List<Task> tasks;


}
