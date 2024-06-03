package io.sillysillyman.todomanagementapp.repository;

import io.sillysillyman.todomanagementapp.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
