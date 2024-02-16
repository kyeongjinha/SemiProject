package com.nf.not404found.mypage.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MyPageCouponDTO {
    private String id;
    private int rate;
    private String comment;
    private String coupon_name;
    private String expiry_date;
    private String date_of_issue;
    private String coupon_status;
}
