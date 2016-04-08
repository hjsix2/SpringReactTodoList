package org.kelog.todolist.service;

import org.kelog.todolist.dao.TodoRepository;
import org.kelog.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TodoServiceImpl implements TodoService {
    
    private TodoRepository repository;
    
    @Autowired
    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Collection<Todo> findAll() {
        return repository.findAll();
    }
    
    @Override
    public Todo findById(int id) {
        return findOrThrow(id);
    }
    
    @Override
    public Todo create(String description, Boolean important) {
        return repository.save(new Todo(description, important));
    }
    
    @Override
    public Todo update(int id, String description, Boolean importance) {
        Todo item = findOrThrow(id);
        
        if (description != null) {
            item.setDescription(description);
        }
        
        if (importance != null) {
            item.setImportant(importance);
        }
        
        return repository.save(item);
    }
    
    @Override
    public void delete(int id) {
        repository.delete(findOrThrow(id));
    }
    
    private Todo findOrThrow(int id) {
        return repository.findById(id).orElseThrow(TodoNotFoundException::new);
    }
}
