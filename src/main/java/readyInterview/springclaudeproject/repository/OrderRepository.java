package readyInterview.springclaudeproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import readyInterview.springclaudeproject.entity.Order;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

//  @EntityGraph(attributePaths = "orderItems")
   // Page<Order> findAll(Pageable pageable);

}
