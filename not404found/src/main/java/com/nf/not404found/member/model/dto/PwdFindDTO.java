package com.nf.not404found.member.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwdFindDTO {
    private int num;
    private String id;
    private String pwd_key;
    private String password;

}
