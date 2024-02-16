package com.nf.not404found.payment.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaymentMapper {
    /**
     * 유저의 쿠폰 상태를 N으로 만들어주는 쿼리
     * @param id 유저의 ID
     * @return 성공 여부 1 OR 0
     */
    @Update("UPDATE user_coupon SET coupon_status = 'N' WHERE id = #{id}")
    int reduceUserCoupon(String id,String couponName);

    /**
     * 유저의 마일리지를 감소시키는 쿼리
     * @param id 유저의 ID
     * @param value 감소 시킬 양
     * @return 성공 여부 1 OR 0
     */
    @Update("UPDATE accounts SET mileage = mileage - #{value} WHERE id = #{id}")
    int reduceUserMileage(String id, int value);

    /**
     * 상품의 수량을 감소시키는 쿼리
     * @param productName 제품 코드
     * @param value 감소시킬 양
     * @return 성공 여부 1 OR 0
     */
    @Update("UPDATE product SET amount = amount - #{value} WHERE name = #{productName}")
    int reduceProductAmount(String productName, int value);

    @Select("SELECT delivery_address FROM address WHERE address = #{receiveaddress} AND address_detail=#{receiveaddressdetail}")
    int selectdliveryCode(String receiveaddress, String receiveaddressdetail);

    @Select("SELECT coupon_number FROM coupon WHERE name=#{coupon}")
    int selectCoupon(String coupon);

    @Insert("INSERT INTO payment (id, delivery_address, product_code, price, amout, payment_date ,deliver_request, delivery_cost, coupon_number) VALUES (#{id}, #{deliveryCode},#{productCode},#{totalprice},#{amount}, DATE(NOW()) ,#{deliveryrequest},#{deliveryCost},#{couponCode})")
    int insertOrder(String id, int deliveryCode, int productCode, int totalprice, String amount, String deliveryrequest, String deliveryCost, int couponCode);

    @Insert("INSERT INTO payment_complete (payment_code) SELECT MAX(payment_code) FROM payment")
    int insertComplete();
}
