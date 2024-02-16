package com.nf.not404found.product.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductCategoryDTO {

    private int category_code;
    private String category_name;
    private int ref_category_code;

}
