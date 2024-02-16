package com.nf.not404found.admin.order.model.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDeliveryDTO {

    private int deliveryCode;
    private int paymentCode;
    private String deliveryId;
    private int deliveryAddress;
    private int productCode;
    private String deliveryStatus;
}
