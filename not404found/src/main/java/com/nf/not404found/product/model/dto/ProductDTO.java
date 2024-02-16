package com.nf.not404found.product.model.dto;

import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    private int product_code;
    private int price;
    private String name;
    private int amount;
    private Date enter_date;
    private String delete_status;
    ProductCategoryDTO productCategory;
    private int average_star_rating;


}
