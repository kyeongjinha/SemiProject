package com.nf.not404found.admin.product.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductDTO {
    private Long productCode;
    private int price;
    private int prices;
    private int priceb;
    private String name;
    private int amount;
    private int amountb;
    private int amounts;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enterDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enterDates;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enterDateb;

    private String deleteStatus;

    private AdminProductCategoryDTO adminProductCategory;
    private AdminOptionDTO adminOption;
    private List<AdminAttachmentDTO> attachmentList;

}
