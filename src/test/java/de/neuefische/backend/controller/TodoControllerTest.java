package de.neuefische.backend.controller;

import de.neuefische.backend.model.Status;
import de.neuefische.backend.model.Todo;
import de.neuefische.backend.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void getAllTodos() throws Exception {
        // given
        List<Todo> todos = new ArrayList<>(
                List.of(
                        new Todo("123", "Do something", Status.OPEN),
                        new Todo("234", "Do something else", Status.OPEN)
                )
        );

        this.todoRepository.setTodos(todos);

        String expectedJson = """
                        [
                            {
                                "id": "123",
                                "description": "Do something",
                                "status": "OPEN"
                            },
                            {
                                "id": "234",
                                "description": "Do something else",
                                "status": "OPEN"
                            }
                        ]
                """;

        // when + then
        this.mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void addTodo() throws Exception {
        // given
        String content = """
                {
                    "description": "Abwaschen",
                    "status": "OPEN"
                }
                """;

        MockHttpServletRequestBuilder post = post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // when + then
        this.mvc.perform(post)
                .andExpect(status().isOk())
                .andExpect(content().json(content))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void getTodoById() throws Exception {
        // given
        Todo todo = new Todo("123", "Paket abholen", Status.OPEN);
        this.todoRepository.addTodo(todo);

        String expectedJson = """
                        {
                            "id": "123",
                            "description": "Paket abholen",
                            "status": "OPEN"
                        }
                """;

        // when + then
        this.mvc.perform(get("/api/todo/123"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }

    @Test
    void getTodoById_returns404IfTodoWithGivenIdIsNotFound() throws Exception {
        this.mvc.perform(get("/api/todo/123"))
                .andExpect(status().isNotFound());
    }
}