package com.tranxuanphong.exercise03.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.tranxuanphong.exercise03.entity.Category;
import com.tranxuanphong.exercise03.entity.Product;
import com.tranxuanphong.exercise03.entity.StaffAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private UUID id;
    private UUID parentId;
    private Category parentCategory;
    private List<Category> subCategories;
    private String category_name;
    private String category_description;
    private String icon;
    private String image;
    private String placeholder;
    private boolean active;
    private Date created_at;
    private Date updated_at;

    private Set<Product> products = new HashSet<>();
}
