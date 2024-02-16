package com.nf.not404found.kakaopay.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApproveResponse {

    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private String item_name;
    private String item_code;
    private Integer quantity;
    private Integer tax_free_amount;
    private Integer vat_amount;
    private String created_at;
    private String approved_at;
    private String payload;
    private Amount amount;
}
