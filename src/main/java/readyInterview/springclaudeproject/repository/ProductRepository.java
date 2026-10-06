package readyInterview.springclaudeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readyInterview.springclaudeproject.entity.Product;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
