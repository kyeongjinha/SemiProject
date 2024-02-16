package com.nf.not404found.mypage.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MyPageOrderDTO {
    private String name;
    private int price;
    private int amount;
    private String payment_date;
    private String delivery_status;
}
