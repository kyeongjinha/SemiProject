package com.nf.not404found.payment.model.service;


import com.nf.not404found.payment.model.dao.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    private final PaymentMapper mapper;

    public PaymentServiceImpl(PaymentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void updateCoupon(String id, String coupon) {
        int result = mapper.reduceUserCoupon(id, coupon);

        if (result > 0 ){
            System.out.println("성공!");
        }else {
            System.out.println("망햇네..");
        }
    }

    @Override
    public void updateMileage(String id, int mileage) {

        int result = mapper.reduceUserMileage(id, mileage);

        if (result > 0 ){
            System.out.println("성공!");
        }else {
            System.out.println("망햇네..");
        }
    }

    @Override
    public void updateAmount(String pname, String amount) {

        int result = mapper.reduceProductAmount(pname, Integer.parseInt(amount));

        if (result > 0 ){
            System.out.println("성공!");
        }else {
            System.out.println("망햇네..");
        }
    }

    @Override
    public int selectdliveryCode(String receiveaddress, String receiveaddressdetail) {

        int result = mapper.selectdliveryCode(receiveaddress,receiveaddressdetail);

        log.info("=============================================> receiveaddress : " + receiveaddress);
        log.info("=============================================> receiveaddressdetail : " + receiveaddressdetail);

        return result;
    }

    @Override
    public int selectCoupon(String coupon) {

        int couponCode = mapper.selectCoupon(coupon);

        return couponCode;
    }

    @Override
    public void insertOrder(String id, int deliveryCode, int productCode, int totalprice, String amount, String deliveryrequest, String deliveryCost, int couponCode) {


        int result = mapper.insertOrder(id, deliveryCode, productCode, totalprice, amount, deliveryrequest, deliveryCost,couponCode );
    }

    @Override
    public void insertComplete() {
        int result = mapper.insertComplete();

        log.info("========================================= 결제완료까지 다 왔냐 ");
    }


}

