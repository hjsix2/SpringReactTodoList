package org.kelog.todolist.dao;

import org.kelog.todolist.model.TodoItem;

import java.util.Collection;

public interface TodoRepository {
    Collection<TodoItem> findAll();
    
    TodoItem findById(int id);
    
    TodoItem create(String description, TodoItem.Importance importance);
    
    TodoItem update(int id, String description, TodoItem.Importance importance);
    
    void delete(int id);
}
