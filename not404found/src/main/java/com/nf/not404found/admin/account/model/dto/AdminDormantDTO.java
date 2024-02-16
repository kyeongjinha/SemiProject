package com.nf.not404found.admin.account.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminDormantDTO {
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enterDate;

    private String status;
    private String dormantTerminationDate;
}
