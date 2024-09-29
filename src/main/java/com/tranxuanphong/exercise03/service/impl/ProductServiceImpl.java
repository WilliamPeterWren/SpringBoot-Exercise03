package com.tranxuanphong.exercise03.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tranxuanphong.exercise03.dto.CategoryDTO;
import com.tranxuanphong.exercise03.dto.ProductDTO;
import com.tranxuanphong.exercise03.entity.Category;
import com.tranxuanphong.exercise03.entity.Product;
import com.tranxuanphong.exercise03.repository.CategoryRepository;
import com.tranxuanphong.exercise03.repository.ProductRepository;
import com.tranxuanphong.exercise03.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);

        Set<Category> categories = new HashSet<>();
        for (CategoryDTO categoryDTO : productDTO.getCategories()) {
            Category category = categoryRepository.findById(categoryDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryDTO.getId()));

            categories.add(category);
        }

        product.setCategories(categories);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + product.getId()));

        existingProduct.setSlug(product.getSlug());
        existingProduct.setProduct_name(product.getProduct_name());
        existingProduct.setSku(product.getSku());
        existingProduct.setSale_price(product.getSale_price());
        existingProduct.setCompare_price(product.getCompare_price());
        existingProduct.setBuying_price(product.getBuying_price());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setShort_description(product.getShort_description());
        existingProduct.setProduct_description(product.getProduct_description());
        existingProduct.setProduct_type(product.getProduct_type());
        existingProduct.setPublished(product.isPublished());
        existingProduct.setDisable_out_of_stock(product.isDisable_out_of_stock());
        existingProduct.setNote(product.getNote());
        existingProduct.setUpdated_at(product.getUpdated_at());

        existingProduct.setCategories(product.getCategories());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
    }

    public List<Product> findProductsByCategoryId(UUID productId) {
        return productRepository.findProductsByCategoryId(productId);
    }
}
