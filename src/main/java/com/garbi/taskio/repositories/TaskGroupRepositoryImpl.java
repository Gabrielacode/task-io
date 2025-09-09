package com.garbi.taskio.repositories;

import com.garbi.taskio.entity.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRepositoryImpl  extends JpaRepository<TaskGroup, Integer> {
}
