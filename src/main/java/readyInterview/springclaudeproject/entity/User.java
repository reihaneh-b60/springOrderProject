package readyInterview.springclaudeproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true,  nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
