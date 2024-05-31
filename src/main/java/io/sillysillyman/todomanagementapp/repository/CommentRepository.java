package io.sillysillyman.todomanagementapp.repository;

import io.sillysillyman.todomanagementapp.entity.Comment;
import io.sillysillyman.todomanagementapp.entity.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTodo(Todo todo);
}
