package com.kargo.service;

import com.kargo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {

    /**
     * Creates a new product.
     * 
     * @param product The product entity to save.
     * @return The saved product.
     */
    Product createProduct(Product product);

    /**
     * Retrieves all products with pagination.
     * 
     * @param pageable Pagination information.
     * @return A page of products.
     */
    Page<Product> getAllProducts(Pageable pageable);

    /**
     * Retrieves a product by its ID.
     * 
     * @param id The ID of the product.
     * @return The product.
     * @throws com.kargo.exception.ProductNotFoundException if not found.
     */
    Product getProductById(Long id);

    /**
     * Updates an existing product.
     * 
     * @param id      The ID of the product to update.
     * @param product The new product details.
     * @return The updated product.
     */
    Product updateProduct(Long id, Product product);

    /**
     * Updates the stock level of a product.
     * 
     * @param id     The ID of the product.
     * @param amount The amount to add (positive) or remove (negative).
     * @return The updated product.
     */
    Product updateStock(Long id, int amount);

    /**
     * Deletes a product by its ID.
     * 
     * @param id The ID of the product to delete.
     */
    void deleteProduct(Long id);

    /**
     * Searches products by name and/or category.
     * 
     * @param name     Optional name filter.
     * @param category Optional category filter.
     * @param pageable Pagination information.
     * @return A page of matching products.
     */
    Page<Product> searchProducts(String name, String category, Pageable pageable);

    /**
     * Retrieves products that are below their minimum stock level.
     * 
     * @return List of low stock products.
     */
    List<Product> getLowStockProducts();

    /**
     * Calculates statistics for the product inventory.
     * 
     * @return A map containing statistics.
     */
    Map<String, Object> getProductStats();
}
