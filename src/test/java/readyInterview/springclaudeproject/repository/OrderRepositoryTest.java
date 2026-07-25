package readyInterview.springclaudeproject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import readyInterview.springclaudeproject.entity.Order;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class OrderRepositoryTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldSaveAndFindOrder() {
        Order order = new Order();
        order.setOrderCode("ORD001");
        order.setOrderName("Test Order");

        orderRepository.save(order);

        List<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1);
    }
}
