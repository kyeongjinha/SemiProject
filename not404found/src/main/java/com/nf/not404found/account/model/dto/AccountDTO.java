package com.nf.not404found.account.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountDTO {

    private String id;
    AccountTypeDTO accountType;
    private String password;
    private String name;
    private String email;
    private String phone_number;
    private Date regist_date;
    private int pay_amount;
    private int mileage;
    private Date last_login;
    private int half_year_accumulated_amount;
    private String account_status;

}
