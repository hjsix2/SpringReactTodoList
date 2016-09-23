package org.kelog.todolist.service;

import org.kelog.todolist.model.Todo;

import java.util.Collection;
import java.util.Optional;

public interface TodoService {
    Collection<Todo> findAll();
    
    Optional<Todo> findById(int id);
    
    Todo create(String description, Boolean important);
    
    Todo update(int id, String description, Boolean importance) throws TodoNotFoundException;
    
    void delete(int id) throws TodoNotFoundException;
}
