package com.nf.not404found.product.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class ProductPageDTO {
//    private List<String> imgName;
//    private List<String> imgPath;
    private String name;
    private String productCode;
    private int discountPrice;
    private int price;
    private int mileage;
    private int deliveryCost;
    private int reviewCount;
    private List<String> color;
}
