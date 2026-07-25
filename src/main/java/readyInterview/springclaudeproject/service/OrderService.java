package readyInterview.springclaudeproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import readyInterview.springclaudeproject.entity.Order;
import readyInterview.springclaudeproject.exception.OrderNotFoundException;
import readyInterview.springclaudeproject.repository.OrderRepository;

import java.text.Collator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


//    public Map<Order.StatusOrder,List<Order>> getOrdersGroupedbyStatus() {
//        List<Order> orders = orderRepository.findAll();
//        return orders.stream()
//                        .collect(Collectors.groupingBy(Order::getStatus));
//    }




//        public Optional<Order> getOrdersByUser(Long userId) {
//
//            return orderRepository.findById(userId);
//        }
//
//        @Transactional
//        public void deleteOrder(Long orderId, Long userId) {
//            Order order = orderRepository.findById(orderId)
//                    .orElseThrow(() -> new OrderNotFoundException(orderId));
//
//            if (!order.getUser().getId().equals(userId) ) {
//                throw new AccessDeniedException("Access denied");
//            }
//            orderRepository.deleteById(orderId);
//            inventoryService.restoreStock(orderId);
//        }

}
