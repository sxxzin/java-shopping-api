package com.devhunter.shop.controller;

import com.devhunter.shop.model.Product;
import com.devhunter.shop.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<Product> list() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("상품 없음"));
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String keyword) {
        return productRepo.findByNameContaining(keyword);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepo.save(product);
    }
}
