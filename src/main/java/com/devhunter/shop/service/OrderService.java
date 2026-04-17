package com.devhunter.shop.service;

import com.devhunter.shop.model.Order;
import com.devhunter.shop.model.Product;
import com.devhunter.shop.repository.OrderRepository;
import com.devhunter.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo,
                        ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    /**
     * 주문 생성
     * - 재고 확인 후 차감
     * - 할인율 적용: 10개 이상 주문 시 10% 할인
     */
    public Order createOrder(Long productId, int quantity) {
        Product product = productRepo.findById(productId)
            .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        if (product.getStock() < quantity) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        // 할인 계산: 10개 이상 → 10% 할인
        double discount = quantity >= 10 ? 0.10 : 0.0;
        double total = product.getPrice() * quantity * (1 - discount);

        // 재고 차감
        product.setStock(product.getStock() - quantity);
        productRepo.save(product);

        // 주문 생성
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(total);
        return orderRepo.save(order);
    }
}
