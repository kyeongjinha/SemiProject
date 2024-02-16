package com.nf.not404found.payment.model.service;

public interface PaymentService {
    void updateCoupon(String id, String coupon);

    void updateMileage(String id, int mileage);

    void updateAmount(String pname, String amount);

    int selectdliveryCode(String receiveaddress, String receiveaddressdetail);

    int selectCoupon(String coupon);

    void insertOrder(String id, int deliveryCode, int productCode, int totalprice, String amount, String deliveryrequest, String deliveryCost, int couponCode);

    void insertComplete();
}
