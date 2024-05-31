package io.sillysillyman.todomanagementapp.dto;

import io.sillysillyman.todomanagementapp.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String content;
    private String userId;
    private Long todoId;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getCommentId();
        this.content = comment.getContent();
        this.userId = comment.getUserId();
        this.todoId = comment.getTodo().getTodoId();
        this.createdAt = comment.getCreatedAt();
    }
}
