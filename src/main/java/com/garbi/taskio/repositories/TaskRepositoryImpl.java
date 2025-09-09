package com.garbi.taskio.repositories;

import com.garbi.taskio.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepositoryImpl  extends JpaRepository<Task, Integer> {
    //Then we implement ourtask repository

}
