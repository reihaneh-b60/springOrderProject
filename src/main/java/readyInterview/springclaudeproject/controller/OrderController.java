package readyInterview.springclaudeproject.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import readyInterview.springclaudeproject.dto.OrderDto;
import readyInterview.springclaudeproject.entity.Order;

import readyInterview.springclaudeproject.entity.OrderItem;
import readyInterview.springclaudeproject.exception.OrderNotFoundException;
import readyInterview.springclaudeproject.repository.OrderItemRepository;
import readyInterview.springclaudeproject.repository.OrderRepository;


import java.util.List;

@RestController
@RequestMapping(("/api/order"))
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderController(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @PostMapping("/addItem")
    public OrderItem addOrderItems(@RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);

    }
    @PostMapping("/addOrder")
    public void createOrder(@RequestBody  @Validated OrderDto orderDto)
    {
        Order order = new Order();
        order.setOrderName(orderDto.getOrderName());
        order.setOrderCode(orderDto.getOrderCode());
        orderRepository.save(order);
    }

    @GetMapping("/all")
    public Page<Order> getOrders(@PageableDefault(size = 10,sort="OrderCode") Pageable pageable) {
        int MAX_PAGE_SIZE = 100;
        if (pageable.getPageSize() > MAX_PAGE_SIZE) {
            pageable = PageRequest.of(pageable.getPageNumber(), MAX_PAGE_SIZE,pageable.getSort());
        }
        Page<Order> orders = orderRepository.findAll(pageable);
        for (Order order : orders) {
            System.out.println(order.getOrderItems());
        }
        return orders;
    }

    @GetMapping("/items")
    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
                return orderRepository.findById(id)
                    .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
