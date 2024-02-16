package com.nf.not404found.admin.event.model.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminEventDTO {
    private int eventCode;
    private int productCode;
    private String name;
    private int discountRate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registEventDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String status;
}
