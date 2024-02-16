package com.nf.not404found.admin.coupon.model.service;

import com.nf.not404found.admin.common.exception.CouponInsertException;
import com.nf.not404found.admin.coupon.model.dto.AdminCouponDTO;

import java.util.List;
import java.util.Map;

public interface AdminCouponService {
    void insertCoupon(AdminCouponDTO coupon) throws CouponInsertException;

    List<AdminCouponDTO> selectAllCoupon();

    List<AdminCouponDTO> selectCondition(Map<String, String> condition);

    List<AdminCouponDTO> selectSection(AdminCouponDTO addPlus);

    void deleteCoupon(List<Integer> chkbox1);

    void supplyCoupon(List<Integer> chkbox1, List<String> chkbox2);
}
