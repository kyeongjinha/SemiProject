package com.nf.not404found.admin.coupon.model.dto;

import lombok.*;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminUserCouponDTO {
    private String id;
    private int couponNumber;
    private Date expiryDate;
    private Date dateOfIssue;
    private String couponStatus;
}
