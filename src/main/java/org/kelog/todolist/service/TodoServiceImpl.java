package org.kelog.todolist.service;

import org.kelog.todolist.dao.TodoRepository;
import org.kelog.todolist.model.Importance;
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
    public Todo create(String description, Importance importance) {
        return repository.save(new Todo(description, importance));
    }
    
    @Override
    public Todo update(int id, String description, Importance importance) {
        Todo item = findOrThrow(id);
        item.setDescription(description);
        item.setImportance(importance);
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
