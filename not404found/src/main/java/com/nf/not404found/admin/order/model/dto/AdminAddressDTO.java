package com.nf.not404found.admin.order.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@ToString
public class AdminAddressDTO {

    private int deliveryAddress;
    private String addressId;
    private int zipCode;
    private String address;
    private String addressDetail;
    private String addressName;

}
