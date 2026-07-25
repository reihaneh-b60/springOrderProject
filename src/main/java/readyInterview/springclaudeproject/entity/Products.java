package readyInterview.springclaudeproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private int price;
    private int quantity;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "productId")
//    private OrderItem orderItem;
}
