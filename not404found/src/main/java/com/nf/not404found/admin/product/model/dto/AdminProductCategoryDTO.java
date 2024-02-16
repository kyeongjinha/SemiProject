package com.nf.not404found.admin.product.model.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductCategoryDTO {
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;
}
