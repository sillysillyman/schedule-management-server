package io.sillysillyman.todomanagementapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Setter
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Comment(String content, String userId, Todo todo) {
        this.content = content;
        this.userId = userId;
        this.todo = todo;
        this.createdAt = LocalDateTime.now();
    }
}