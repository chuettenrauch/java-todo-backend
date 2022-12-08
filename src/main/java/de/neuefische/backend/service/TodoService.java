package de.neuefische.backend.service;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> listTodos() {
        return this.todoRepository.getTodos();
    }

    public Todo addTodo(Todo todo) {
        todo.setId(this.generateRandomId());

        return this.todoRepository.addTodo(todo);
    }

    private String generateRandomId() {
        return UUID.randomUUID().toString();
    }
}
