package io.sillysillyman.todomanagementapp.repository;

import io.sillysillyman.todomanagementapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoManagementRepository extends JpaRepository<Todo, Long> {

}
