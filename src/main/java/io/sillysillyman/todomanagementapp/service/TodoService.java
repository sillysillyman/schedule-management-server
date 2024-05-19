package io.sillysillyman.todomanagementapp.service;

import io.sillysillyman.todomanagementapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
}
