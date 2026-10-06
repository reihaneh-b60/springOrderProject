package readyInterview.springclaudeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readyInterview.springclaudeproject.entity.IdempotencyRecord;

public interface IdempotencyRecordRepository extends JpaRepository<IdempotencyRecord, String> {
}
