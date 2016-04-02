package org.kelog.todolist.dao;

import org.kelog.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Optional<Todo> findById(int id);
}
