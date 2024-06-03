package io.sillysillyman.todomanagementapp.dto;

import io.sillysillyman.todomanagementapp.entity.Todo;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponse {

    private Long todoId;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;

    public TodoResponse(Todo todo) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userId = todo.getUserId();
        this.createdAt = todo.getCreatedAt();
    }
}
