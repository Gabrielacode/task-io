package com.garbi.taskio.repositories;

import com.garbi.taskio.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepositoryImpl  extends JpaRepository<Task, Integer> {
    //Then we implement our task repository

    //This is so theat we can check if there is a task that belongs to this id and has the group id of this
    Optional<Task> findTaskByIdAndTaskGroupId(Integer taskId, Integer taskGroupId);
   List<Task> findTasksByTaskGroupId(Integer taskGroupId);

}
