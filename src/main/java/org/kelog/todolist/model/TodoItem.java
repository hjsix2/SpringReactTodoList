package org.kelog.todolist.model;

import java.util.Objects;

public class TodoItem {
    
    private String description;
    private Importance importance;
    private int id;

    public TodoItem(String description, Importance importance, int id) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(importance);
        if (id < 0) {
            throw new IllegalArgumentException("id can't be negative");
        }
        
        this.description = description;
        this.importance = importance;
        this.id = id;
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

        TodoItem todoItem = (TodoItem) o;

        return id == todoItem.id && (description != null ? description.equals(todoItem.description) : todoItem.description == null && importance == todoItem.importance);
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (importance != null ? importance.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    public enum Importance {
        LOW, NORMAL, HIGH
    }
}
