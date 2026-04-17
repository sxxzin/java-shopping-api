package com.devhunter.shop.controller;

import com.devhunter.shop.model.Order;
import com.devhunter.shop.repository.OrderRepository;
import com.devhunter.shop.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepo;

    public OrderController(OrderService orderService,
                           OrderRepository orderRepo) {
        this.orderService = orderService;
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public List<Order> list() {
        return orderRepo.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Map<String, Object> body) {
        Long productId = ((Number) body.get("productId")).longValue();
        int quantity = ((Number) body.get("quantity")).intValue();
        return orderService.createOrder(productId, quantity);
    }
}
