package de.neuefische.backend.repository;

import de.neuefische.backend.model.Todo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@NoArgsConstructor
public class TodoRepository {
    private final Map<String, Todo> todos = new HashMap<>();

    public TodoRepository(List<Todo> todos) {
        this.setTodos(todos);
    }

    public Todo addTodo(Todo todo) {
        this.todos.put(todo.getId(), todo);

        return todo;
    }

    public List<Todo> getTodos() {
        return new ArrayList<>(this.todos.values());
    }

    public void setTodos(List<Todo> todos) {
        for (Todo todo : todos) {
            this.addTodo(todo);
        }
    }

    public Todo getTodoById(String id) {
        return this.todos.get(id);
    }
}
