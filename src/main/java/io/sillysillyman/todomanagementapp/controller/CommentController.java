package io.sillysillyman.todomanagementapp.controller;

import io.sillysillyman.todomanagementapp.dto.CommentRequestDto;
import io.sillysillyman.todomanagementapp.dto.CommentResponseDto;
import io.sillysillyman.todomanagementapp.entity.Comment;
import io.sillysillyman.todomanagementapp.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
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

@RestController
@RequestMapping("v1.0/todo/{todoId}/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Post a new comment")
    @PostMapping
    public ResponseEntity<Comment> postComment(@PathVariable Long todoId,
        @Valid @RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.createComment(todoId, requestDto);
        CommentResponseDto responseDto = new CommentResponseDto(comment);
        return ResponseEntity.ok().body(comment);
    }

    @Operation(summary = "Get a comment by Id")
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.readComment(commentId);
        CommentResponseDto responseDto = new CommentResponseDto(comment);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get all comments for a specific todo")
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getCommentsByTodoId(@PathVariable Long todoId) {
        List<Comment> comments = commentService.readCommentsByTodoId(todoId);
        List<CommentResponseDto> responseDtos = comments.stream()
            .map(CommentResponseDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(responseDtos);
    }

    @Operation(summary = "Update a comment by ID")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> putComment(@PathVariable Long commentId,
        @Valid @RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.updateComment(commentId, requestDto);
        CommentResponseDto responseDto = new CommentResponseDto(comment);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Delete a comment by ID")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
