package io.sillysillyman.todomanagementapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column
    private String role;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role != null ? role : "USER";
        this.createdAt = LocalDateTime.now();
    }
}
