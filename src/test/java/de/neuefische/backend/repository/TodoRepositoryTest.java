package de.neuefische.backend.repository;

import de.neuefische.backend.model.Status;
import de.neuefische.backend.model.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoRepositoryTest {

    @Test
    void getTodos_returnsEmptyListPerDefault() {
        TodoRepository sut = new TodoRepository();

        assertEquals(new ArrayList<>(), sut.getTodos());
    }

    @Test
    void getTodos_returnsListOfTodos() {
        // given
        List<Todo> expected = new ArrayList<>(
                List.of(
                        new Todo("123", "Do something", Status.OPEN),
                        new Todo("234", "Do something else", Status.OPEN)
                )
        );

        // when
        TodoRepository sut = new TodoRepository(expected);
        List<Todo> actual = sut.getTodos();

        // then
        assertEquals(expected, actual);
    }

    @Test
    void addTodo_addsTodoToList() {
        // given
        Todo expected = new Todo("123", "Do something", Status.OPEN);

        // when
        TodoRepository sut = new TodoRepository();
        Todo actual = sut.addTodo(expected);

        // then
        assertEquals(expected, actual);
        assertTrue(sut.getTodos().contains(expected));
    }

    @Test
    void getTodoById_returnsTodo() {
        // given
        Todo expected = new Todo("999", "Whatever", Status.OPEN);

        List<Todo> todos = List.of(
                new Todo("123", "Do something", Status.OPEN),
                expected,
                new Todo("234", "Do something else", Status.OPEN)
        );

        // when
        TodoRepository sut = new TodoRepository(todos);
        Todo actual = sut.getTodoById(expected.getId());

        // then
        assertEquals(expected, actual);
    }

    @Test
    void getTodoById_returnsNullIfTodoWithGivenIdIsNotFound() {
        // given
        Todo expected = new Todo("999", "Whatever", Status.OPEN);

        List<Todo> todos = List.of(
                new Todo("123", "Do something", Status.OPEN),
                new Todo("234", "Do something else", Status.OPEN)
        );

        // when
        TodoRepository sut = new TodoRepository(todos);
        Todo actual = sut.getTodoById(expected.getId());

        // then
        assertNull(actual);
    }
}