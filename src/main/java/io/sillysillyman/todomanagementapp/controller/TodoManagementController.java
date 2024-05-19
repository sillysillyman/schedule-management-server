package io.sillysillyman.todomanagementapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoManagementController {

    @PostMapping("/v1.0/todo")
    public ResponseEntity postTodo() {
        return ResponseEntity.ok().build();
    }
}
