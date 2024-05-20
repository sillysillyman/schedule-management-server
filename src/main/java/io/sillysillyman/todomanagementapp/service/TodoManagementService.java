package io.sillysillyman.todomanagementapp.service;

import io.sillysillyman.todomanagementapp.dto.TodoManagementRequestDto;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.repository.TodoManagementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoManagementService {

    private final TodoManagementRepository todoManagementRepository;

    public Todo createTodo(TodoManagementRequestDto dto) {
        Todo newTodo = dto.toEntity();
        return todoManagementRepository.save(newTodo);
    }
}
