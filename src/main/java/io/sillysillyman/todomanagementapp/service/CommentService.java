package io.sillysillyman.todomanagementapp.service;

import io.sillysillyman.todomanagementapp.dto.CommentRequestDto;
import io.sillysillyman.todomanagementapp.entity.Comment;
import io.sillysillyman.todomanagementapp.entity.Todo;
import io.sillysillyman.todomanagementapp.exception.CommentNotFoundException;
import io.sillysillyman.todomanagementapp.exception.TodoNotFoundException;
import io.sillysillyman.todomanagementapp.repository.CommentRepository;
import io.sillysillyman.todomanagementapp.repository.TodoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment createComment(Long todoId, CommentRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId)
            .orElseThrow(() -> new TodoNotFoundException("해당 일정을 찾을 수 없습니다."));
        Comment comment = requestDto.toEntity(todo);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment readComment(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new CommentNotFoundException("해당 댓글을 찾을 수 없습니다."));
    }

    @Transactional
    public List<Comment> readCommentsByTodoId(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
            .orElseThrow(() -> new TodoNotFoundException("해당 일정을 찾을 수 없습니다."));
        return commentRepository.findByTodo(todo);
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new CommentNotFoundException("해당 댓글을 찾을 수 없습니다."));
        comment.setContent(requestDto.getContent());
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new CommentNotFoundException("해당 댓글을 찾을 수 없습니다."));
    }
}
