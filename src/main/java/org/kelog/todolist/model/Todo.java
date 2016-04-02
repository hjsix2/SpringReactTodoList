package org.kelog.todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@SuppressWarnings("unused")
public class Todo {
    
    private String description;
    private Importance importance;
    
    @Id
    @GeneratedValue
    private int id;
    
    public Todo() { // JPA
    }
    
    public Todo(String description, Importance importance) {
        this.description = Objects.requireNonNull(description);
        this.importance = Objects.requireNonNull(importance);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Importance getImportance() {
        return importance;
    }
    
    public void setImportance(Importance importance) {
        this.importance = importance;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Todo todo = (Todo) o;
        
        return id == todo.id && (description != null ? description.equals(todo.description) : todo.description == null && importance == todo.importance);
    }
    
    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
