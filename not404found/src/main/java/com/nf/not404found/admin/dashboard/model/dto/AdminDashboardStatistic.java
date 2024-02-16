package com.nf.not404found.admin.dashboard.model.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class AdminDashboardStatistic {
    private int visitCount;  //임의 값
    private int dailyPaymentCount;
    private int dailyPaymentAmount;
    private int dailyRegistrations;
    private int totalMembers;
    private int pendingDormantAccountNotification;
    private int totalAccumulatedPoints;
    private int suspiciousAccessAttempts;
}
