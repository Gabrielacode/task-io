package com.garbi.taskio.repositories;

import com.garbi.taskio.entity.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskGroupRepositoryImpl  extends JpaRepository<TaskGroup, Integer> {
}
