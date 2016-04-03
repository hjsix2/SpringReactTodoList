package org.kelog.todolist.service;

import org.kelog.todolist.model.Todo;

import java.util.Collection;

public interface TodoService {
    Collection<Todo> findAll();
    
    Todo findById(int id) throws TodoNotFoundException;
    
    Todo create(String description, Boolean importance);
    
    Todo update(int id, String description, Boolean importance) throws TodoNotFoundException;
    
    void delete(int id) throws TodoNotFoundException;
}
