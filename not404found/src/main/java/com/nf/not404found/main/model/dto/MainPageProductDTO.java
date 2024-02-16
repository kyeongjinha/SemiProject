package com.nf.not404found.main.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MainPageProductDTO {
    private String imgPath;
    private String imgName;
    private String name;    //상품 이름
    private float discountRate; //상품 할인 가격
    private int discountPrice;
    private int price;  //상품 가격
    private int reviewCount;    //상품 리뷰 개수
    private int priority;   //상품 중요도
}
