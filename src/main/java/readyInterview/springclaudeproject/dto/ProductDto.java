package readyInterview.springclaudeproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private String name;
    private int quantity;
    private BigDecimal price;
}
