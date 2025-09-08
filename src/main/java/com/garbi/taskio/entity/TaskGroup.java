package com.garbi.taskio.entity;

import com.garbi.taskio.constants.TaskGroupPriority;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    TaskGroupPriority priority;

    @OneToMany(mappedBy = "taskGroup")
    List<Task> tasks;


}
