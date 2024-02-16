package com.nf.not404found.admin.dashboard.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter@ToString
public class DashboardDTO {
    private int orderReception;
    private int productPreparation;
    private int preparationShipment;
    private int shipping;
    private int deliveryCompleted;
}
