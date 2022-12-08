package de.neuefische.backend.repository;

import de.neuefische.backend.model.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TodoRepository {
    private List<Todo> todos = new ArrayList<>();

    public Todo addTodo(Todo todo) {
        this.todos.add(todo);

        return todo;
    }
}
