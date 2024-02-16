package com.nf.not404found.mypage.model.dto;

import com.nf.not404found.product.model.dto.ProductDTO;

public class PaymentDTO {
    private AddrDTO addr;    //
    private ProductDTO product;
    private int price;
    private int amount;
    private String  paymentDate;
    private String deliveryRequest;
    private int deliveryCost;
    private int couponNumber;
}
