package readyInterview.springclaudeproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readyInterview.springclaudeproject.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
