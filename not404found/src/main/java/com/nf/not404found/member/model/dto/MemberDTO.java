package com.nf.not404found.member.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MemberDTO {
    private String id;
    private String email;
    private String pwd;
    private String name;
    private String phone;
}
