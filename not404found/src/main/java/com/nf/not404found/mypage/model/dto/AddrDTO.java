package com.nf.not404found.mypage.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class AddrDTO {
    private int deliveryCode;
    private String id;
    private String address;
    private String zonecode;
    private String name;
    private String addrDetail;
}
