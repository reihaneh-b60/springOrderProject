package readyInterview.springclaudeproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;

    @Column(precision =  10, scale = 2)
    private BigDecimal price;
    private int quantity;

    @Version
    private int version;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "productId")
//    private OrderItem orderItem;
}
