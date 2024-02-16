package com.nf.not404found.admin.coupon.model.dao;


import com.nf.not404found.admin.coupon.model.dto.AdminCouponDTO;
import com.nf.not404found.admin.coupon.model.dto.AdminUserCouponDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminCouponMapper {

    int insertCoupon(AdminCouponDTO coupon);

    List<AdminCouponDTO> selectAllCoupon();

    List<AdminCouponDTO> selectCondition(Map<String, String> condition);

    List<AdminCouponDTO> selectSection(AdminCouponDTO addPlus);

    void deleteCoupon(Integer integer);

//    void supplyCoupon(Integer integer, String s);

    void supplyCoupon(AdminUserCouponDTO user);
}
