package de.neuefische.backend.service;

import de.neuefische.backend.generator.IdGeneratorInterface;
import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    private final IdGeneratorInterface idGenerator;

    public List<Todo> listTodos() {
        return this.todoRepository.getTodos();
    }

    public Todo addTodo(Todo todo) {
        String randomId = this.idGenerator.generateId();
        todo.setId(randomId);

        return this.todoRepository.addTodo(todo);
    }

    public Todo getTodoById(String id) {
        return this.todoRepository.getTodoById(id);
    }

    public Todo updateTodoById(String id, Todo todo) {
        todo.setId(id);

        return this.todoRepository.addTodo(todo);
    }

    public Todo deleteTodoById(String id) {
        return this.todoRepository.deleteTodoById(id);
    }

    public boolean todoWithIdExists(String id) {
        return this.todoRepository.containsId(id);
    }
}
