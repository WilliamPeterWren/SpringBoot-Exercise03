package com.tranxuanphong.exercise03.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "slug", columnDefinition = "TEXT", unique = true)
    private String slug;

    @Column(name = "product_name", unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private String product_name;

    @Column(name = "sku", columnDefinition = "VARCHAR(255)")
    private String sku;

    @Column(name = "sale_price", nullable = false, columnDefinition = "NUMERIC(10, 2) DEFAULT 0")
    private double sale_price;

    @Column(name = "compare_price", nullable = false, columnDefinition = "NUMERIC(10, 2) DEFAULT 0")
    private double compare_price;

    @Column(name = "buying_price", columnDefinition = "NUMERIC(10, 2) DEFAULT NULL")
    private double buying_price;

    @Column(name = "quantity", nullable = false, columnDefinition = "NUMERIC(10,2) DEFAULT 0")
    private double quantity;

    @Column(name = "short_description", nullable = false, columnDefinition = "VARCHAR(165)")
    private String short_description;

    @Column(name = "product_description", nullable = false, columnDefinition = "TEXT")
    private String product_description;

    @Column(name = "product_type", nullable = false, columnDefinition = "VARCHAR(64)")
    private String product_type;

    @Column(name = "published", columnDefinition = "tinyint(1) DEFAULT 0")
    private boolean published;

    @Column(name = "disable_out_of_stock", columnDefinition = "tinyint(1) DEFAULT 1")
    private boolean disable_out_of_stock;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        created_at = now;
        updated_at = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = new Date();
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "products_categories", joinColumns = {
            @JoinColumn(name = "product_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(UUID categoryId) {
        Category category = this.categories.stream().filter(t -> t.getId().equals(categoryId)).findFirst().orElse(null);
        if (category != null) {
            this.categories.remove(category);
            category.getProducts().remove(this);
        }
    }
}
