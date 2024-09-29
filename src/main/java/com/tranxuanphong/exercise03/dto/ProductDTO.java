package com.tranxuanphong.exercise03.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.tranxuanphong.exercise03.entity.StaffAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private UUID id;
    private String slug;
    private String product_name;
    private String sku;
    private double sale_price;
    private double compare_price;
    private double buying_price;
    private double quantity;
    private String short_description;
    private String product_description;
    private String product_type;
    private boolean published;
    private boolean disable_out_of_stock;
    private String note;
    private Date created_at;
    private Date updated_at;

    private Set<CategoryDTO> categories = new HashSet<>();
}
