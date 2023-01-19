package com.naoto.todolist.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naoto.todolist.domain.model.TodoList;

public interface TodoRepository extends JpaRepository<TodoList, Long> {
    
}
