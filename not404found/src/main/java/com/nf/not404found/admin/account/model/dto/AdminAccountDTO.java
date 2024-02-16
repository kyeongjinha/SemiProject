package com.nf.not404found.admin.account.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminAccountDTO {
    private String id;
    private AdminAccountTypeDTO accountTypeName;
    private String name;
    private String email;
    private String accountStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registDate;

    private int payAmount;
    private int mileage;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastLogin;

    private int halfYearAccumulatedAmount;

    private AdminAccountStatusDTO accountStatusList;
}
