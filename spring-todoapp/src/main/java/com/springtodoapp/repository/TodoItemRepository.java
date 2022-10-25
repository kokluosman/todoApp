package com.springtodoapp.repository;

import com.springtodoapp.entities.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {
}
