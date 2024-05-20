package io.sillysillyman.todomanagementapp.controller;

import io.sillysillyman.todomanagementapp.dto.TodoManagementRequestDto;
import io.sillysillyman.todomanagementapp.service.TodoManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TodoManagementController {

    public final TodoManagementService todoManagementService;

    @PostMapping("/v1.0/todo")
    public ResponseEntity postTodo(@RequestBody TodoManagementRequestDto dto) {
        todoManagementService.createTodo(dto);
        return ResponseEntity.ok().build();
    }
}
