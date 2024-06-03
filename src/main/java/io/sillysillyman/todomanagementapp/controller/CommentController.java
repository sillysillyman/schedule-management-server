package io.sillysillyman.todomanagementapp.controller;

import io.sillysillyman.todomanagementapp.dto.CommentRequest;
import io.sillysillyman.todomanagementapp.dto.CommentResponse;
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
    public ResponseEntity<CommentResponse> postComment(@PathVariable Long todoId,
        @Valid @RequestBody CommentRequest request) {
        Comment comment = commentService.createComment(todoId, request);
        CommentResponse response = new CommentResponse(comment);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Get a comment by Id")
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.readComment(commentId);
        CommentResponse response = new CommentResponse(comment);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Get all comments for a specific todo")
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getCommentsByTodoId(@PathVariable Long todoId) {
        List<Comment> comments = commentService.readCommentsByTodoId(todoId);
        List<CommentResponse> responses = comments.stream()
            .map(CommentResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(responses);
    }

    @Operation(summary = "Update a comment by ID")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> putComment(@PathVariable Long commentId,
        @Valid @RequestBody CommentRequest request) {
        Comment comment = commentService.updateComment(commentId, request);
        CommentResponse response = new CommentResponse(comment);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete a comment by ID")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
