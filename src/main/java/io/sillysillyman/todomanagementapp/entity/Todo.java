package io.sillysillyman.todomanagementapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Todo(String title, String content, String userId, String password) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.password = password;
        this.comments = new LinkedList<>();
        this.createdAt = LocalDateTime.now();
    }
}
