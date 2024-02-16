package com.nf.not404found.order.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class OrderDTO {
    private String grade;
    private float mileageRate;
    private int mileage;
    private String productName;
    private int price;
    private Date paymentDate;
    private int paymentCode;
}
