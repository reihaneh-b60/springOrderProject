package readyInterview.springclaudeproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class IdempotencyRecord {


    @Id
    private String idempotencyKey;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "TEXT")
    private String response;


    public enum Status {
        IN_PROGRESS,
        COMPLETED,
        PENDING,
    }
}
