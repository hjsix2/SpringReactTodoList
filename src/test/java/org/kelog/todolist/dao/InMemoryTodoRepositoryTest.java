package org.kelog.todolist.dao;

import org.junit.Before;
import org.junit.Test;
import org.kelog.todolist.model.TodoItem;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryTodoRepositoryTest {

    private TodoRepository repo;

    @Before
    public void before() {
        repo = new InMemoryTodoRepository();
    }

    @Test
    public void should_return_empty_collection_when_database_is_empty() {
        assertThat(repo.findAll()).hasSize(0);
    }

    @Test
    public void should_create_new_item_and_get_it_back() {
        int id = repo.create("somedesc", TodoItem.Importance.HIGH).getId();
        TodoItem item = repo.findById(id);
        assertThat(item.getDescription()).isEqualTo("somedesc");
        assertThat(item.getImportance()).isEqualTo(TodoItem.Importance.HIGH);
    }

    @Test(expected = TodoItemNotFoundException.class)
    public void should_throw_if_item_is_not_found_when_finding() {
        repo.findById(42);
    }

    @Test
    public void should_update_item() {
        int id = repo.create("somedesc", TodoItem.Importance.HIGH).getId();
        TodoItem updated = repo.update(id, "newdesc", TodoItem.Importance.LOW);
        assertThat(updated.getDescription()).isEqualTo("newdesc");
        assertThat(updated.getImportance()).isEqualTo(TodoItem.Importance.LOW);
    }
    
    @Test(expected = TodoItemNotFoundException.class)
    public void should_throw_if_item_is_not_found_when_updating() {
        repo.update(42, "newdesc", TodoItem.Importance.LOW);
    }

    @Test
    public void should_delete_item() {
        int id = repo.create("somedesc", TodoItem.Importance.HIGH).getId();
        repo.delete(id);
        assertThat(repo.findAll()).hasSize(0);
    }
    
    @Test(expected = TodoItemNotFoundException.class)
    public void should_throw_if_item_is_not_found_when_deleting() {
        repo.delete(42);
    }
}
