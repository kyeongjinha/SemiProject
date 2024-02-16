package com.nf.not404found.admin.order.model.dto;


import com.nf.not404found.admin.coupon.model.dto.AdminCouponDTO;
import com.nf.not404found.admin.product.model.dto.AdminProductDTO;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminOrderDTO {

    private int paymentCode;
    private String paymentId;
    private AdminAddressDTO addressList;
    private AdminDeliveryDTO deliveryList;
    private AdminProductDTO productList;
    private AdminCouponDTO couponList;
    private int price;
    private int amount;
    private Date paymentDate;
    private String deliveryRequest;
    private int deliveryCost;
    private int couponNumber;

}
