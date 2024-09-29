package com.tranxuanphong.exercise03.service;

import java.util.List;
import java.util.UUID;

import com.tranxuanphong.exercise03.dto.ProductDTO;
import com.tranxuanphong.exercise03.entity.Product;

public interface ProductService {
    List<Product> findProductsByCategoryId(UUID productId);

    public ProductDTO createProduct(ProductDTO productDTO);

    Product getProductById(UUID productId);

    List<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(UUID productId);
}
