package readyInterview.springclaudeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readyInterview.springclaudeproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
