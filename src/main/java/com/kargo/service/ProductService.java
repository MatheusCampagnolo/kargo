package com.kargo.service;

import com.kargo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product createProduct(Product product);

    Page<Product> getAllProducts(Pageable pageable);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    Product updateStock(Long id, int amount);

    void deleteProduct(Long id);

    Page<Product> searchProducts(String name, String category, Pageable pageable);

    List<Product> getLowStockProducts();

    Map<String, Object> getProductStats();
}
