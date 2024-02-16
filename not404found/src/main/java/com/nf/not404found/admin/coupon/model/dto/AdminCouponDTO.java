package com.nf.not404found.admin.coupon.model.dto;

import com.nf.not404found.admin.account.model.dto.AdminAccountDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminCouponDTO {
    private int couponNumber;
    private String name;
    private int rate;
    private String comment;
    private int period;
    private int periods;
    private int periode;
    private int searchRates;
    private int searchRatee;
    private AdminAccountDTO adminAccountList;
}
