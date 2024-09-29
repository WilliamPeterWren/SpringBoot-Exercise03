package com.tranxuanphong.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.tranxuanphong.exercise03.entity.Category;
import com.tranxuanphong.exercise03.service.CategoryService;
import com.tranxuanphong.exercise03.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(UUID categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.get();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        Category existingCategory = categoryRepository.findById(category.getId()).get();

        existingCategory.setParentId(category.getParentId());
        existingCategory.setParentCategory(category.getParentCategory());
        existingCategory.setSubCategories(category.getSubCategories());
        existingCategory.setCategory_name(category.getCategory_name());
        existingCategory.setCategory_description(category.getCategory_description());
        existingCategory.setIcon(category.getIcon());
        existingCategory.setImage(category.getImage());
        existingCategory.setPlaceholder(category.getPlaceholder());
        existingCategory.setActive(category.isActive());
        existingCategory.setUpdated_at(category.getUpdated_at());

        existingCategory.setProducts(category.getProducts());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return updatedCategory;
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> findCategorysByProductsId(UUID productId) {
        List<Category> categories = categoryRepository.findCategoriesByProductsId(productId);
        return categories;
    }
}
