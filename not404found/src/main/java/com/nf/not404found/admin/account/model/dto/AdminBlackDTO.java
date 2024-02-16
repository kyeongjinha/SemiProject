package com.nf.not404found.admin.account.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminBlackDTO {
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date blackDate;

    private String reason;
    private String email;
}
