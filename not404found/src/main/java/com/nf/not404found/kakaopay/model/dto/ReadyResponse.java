package com.nf.not404found.kakaopay.model.dto;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReadyResponse {

    private String tid;
    private String next_redirect_pc_url;
    private String partner_order_id;
}
