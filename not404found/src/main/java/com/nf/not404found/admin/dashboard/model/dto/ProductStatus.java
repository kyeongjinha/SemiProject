package com.nf.not404found.admin.dashboard.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class ProductStatus {
    private int sale;               //판매중
    private int sellingStop;        //판매 중지
    private int specialPrice;       //특가 상품
    private int amountStatus;             //재고 낮음 수량 10 이하
}
