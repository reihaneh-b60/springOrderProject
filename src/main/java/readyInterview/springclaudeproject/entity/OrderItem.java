package readyInterview.springclaudeproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_Item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long productId;
    private int orderQuantity;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Order orders;
}
