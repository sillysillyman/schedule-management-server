package io.sillysillyman.todomanagementapp.controller;

import io.sillysillyman.todomanagementapp.dto.TodoManagementRequestDto;
import io.sillysillyman.todomanagementapp.dto.TodoManagementResponseDto;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.service.TodoManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TodoManagementController {

    public final TodoManagementService todoManagementService;

    @PostMapping("/v1.0/todo")
    public ResponseEntity<TodoManagementResponseDto> postTodo(
        @RequestBody TodoManagementRequestDto dto) {
        Todo todo = todoManagementService.createTodo(dto);
        TodoManagementResponseDto responseDto = new TodoManagementResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/v1.0/todo/{todoId}")
    public ResponseEntity<TodoManagementResponseDto> getTodo(@PathVariable Long todoId) {
        Todo todo = todoManagementService.getTodo(todoId);
        TodoManagementResponseDto responseDto = new TodoManagementResponseDto(todo);
        return ResponseEntity.ok().body(responseDto);
    }
}
