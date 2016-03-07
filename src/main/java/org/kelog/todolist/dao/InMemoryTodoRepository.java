package org.kelog.todolist.dao;

import org.kelog.todolist.model.TodoItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class InMemoryTodoRepository implements TodoRepository {

    private Set<TodoItem> db = new HashSet<>();
    private int nextId = 1;

//  Uncomment for ad-hoc testing
//    {
//        create("Learn Java", TodoItem.Importance.HIGH);
//        create("Get rich", TodoItem.Importance.LOW);
//    }

    @Override
    public Collection<TodoItem> findAll() {
        return Collections.unmodifiableCollection(db);
    }

    @Override
    public TodoItem findById(int id) throws TodoItemNotFoundException {
        return db.stream()
                .filter(item -> item.getId() == id)
                .findAny()
                .orElseThrow(TodoItemNotFoundException::new);
    }

    @Override
    public TodoItem create(String description, TodoItem.Importance importance) {
        TodoItem item = new TodoItem(description, importance, nextId++);
        db.add(item);
        return item;
    }

    @Override
    public TodoItem update(int id, String description, TodoItem.Importance importance) throws TodoItemNotFoundException {
        TodoItem item = findById(id);
        item.setDescription(description);
        item.setImportance(importance);
        return item;
    }

    @Override
    public void delete(int id) throws TodoItemNotFoundException {
        db.remove(findById(id));
    }
}
