package de.neuefische.backend.service;

import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Todo getTodoById(String id) {
        Todo todo = this.todoRepository.getTodoById(id);

        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return todo;
    }
}
