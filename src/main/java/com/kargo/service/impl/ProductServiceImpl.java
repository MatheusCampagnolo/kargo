package com.kargo.service.impl;

import com.kargo.domain.Product;
import com.kargo.exception.ProductNotFoundException;
import com.kargo.repository.ProductRepository;
import com.kargo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        if (productRepository.existsBySku(product.getSku())) {
            throw new IllegalArgumentException("Product with SKU " + product.getSku() + " already exists.");
        }
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);

        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());
        product.setPrice(productDetails.getPrice());
        product.setMinStockLevel(productDetails.getMinStockLevel());
        product.setQuantity(productDetails.getQuantity());

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateStock(Long id, int amount) {
        Product product = getProductById(id);
        int newQuantity = product.getQuantity() + amount;

        if (newQuantity < 0) {
            throw new IllegalArgumentException("Insufficient stock. Cannot reduce quantity below 0.");
        }

        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> searchProducts(String name, String category, Pageable pageable) {
        if (name != null && category != null) {
            return productRepository.findByNameContainingIgnoreCaseAndCategoryContainingIgnoreCase(name, category,
                    pageable);
        } else if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (category != null) {
            return productRepository.findByCategoryContainingIgnoreCase(category, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    @Override
    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts();
    }

    @Override
    public Map<String, Object> getProductStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalItems", productRepository.countTotalItems());
        stats.put("totalInventoryValue", productRepository.calculateTotalInventoryValue());
        stats.put("mostExpensiveProduct", productRepository.findMostExpensiveProduct().orElse(null));
        return stats;
    }
}
