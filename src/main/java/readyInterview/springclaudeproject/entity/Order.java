package readyInterview.springclaudeproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "orderCode")
@Table(name = "orders")
public class Order {

    public enum StatusOrder{
        PENDING,COMPLETED,CANCELED,SHIPPED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderCode;
    private String orderName;
    private StatusOrder status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;


}
