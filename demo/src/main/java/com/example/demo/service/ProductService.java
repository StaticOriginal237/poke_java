package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public CompletableFuture<Product> findByIdAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> findById(id), executorService);
    }

    public CompletableFuture<Product> saveAsync(Product product) {
        return CompletableFuture.supplyAsync(() -> save(product), executorService);
    }
}