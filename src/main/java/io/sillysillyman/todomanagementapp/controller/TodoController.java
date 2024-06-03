package io.sillysillyman.todomanagementapp.controller;

import io.sillysillyman.todomanagementapp.dto.TodoRequest;
import io.sillysillyman.todomanagementapp.dto.TodoResponse;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1.0/todo")
@RestController
@AllArgsConstructor
public class TodoController {

    public final TodoService todoManagementService;

    @Operation(summary = "Create a new todo")
    @PostMapping
    public ResponseEntity<TodoResponse> postTodo(
        @Valid @RequestBody TodoRequest request) {
        Todo todo = todoManagementService.createTodo(request);
        TodoResponse response = new TodoResponse(todo);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Get a todo by ID")
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable Long todoId) {
        Todo todo = todoManagementService.readTodo(todoId);
        TodoResponse response = new TodoResponse(todo);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Get all todos")
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodos() {
        List<Todo> todos = todoManagementService.readTodos();
        List<TodoResponse> responses = todos.stream()
            .map(TodoResponse::new).toList();
        return ResponseEntity.ok().body(responses);
    }

    @Operation(summary = "Update a todo by ID")
    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponse> putTodo(@PathVariable Long todoId,
        @Valid @RequestBody TodoRequest request) {
        Todo todo = todoManagementService.updateTodo(todoId, request);
        TodoResponse response = new TodoResponse(todo);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete a todo by ID")
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId,
        @Valid @RequestBody TodoRequest request) {
        todoManagementService.deleteTodo(todoId, request.getPassword());
        return ResponseEntity.ok().build();
    }
}
