package com.nf.not404found.admin.coupon.model.service;

import com.nf.not404found.admin.common.exception.CouponInsertException;
import com.nf.not404found.admin.coupon.model.dao.AdminCouponMapper;
import com.nf.not404found.admin.coupon.model.dto.AdminCouponDTO;
import com.nf.not404found.admin.coupon.model.dto.AdminUserCouponDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class AdminCouponServiceImpl implements AdminCouponService {

    private final AdminCouponMapper mapper;

    public AdminCouponServiceImpl(AdminCouponMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void insertCoupon(AdminCouponDTO coupon) throws CouponInsertException {

        log.info("============================> 쿠폰등록 서비스 ");

        int result = mapper.insertCoupon(coupon);

        System.out.println(result);
        if (!(result > 0)) {
            log.info("==========================> 쿠폰 등록 실패 ");
            throw new CouponInsertException("쿠폰 등록에 실패하셨습니다. ");
        }
    }

    @Override
    public List<AdminCouponDTO> selectAllCoupon() {

        log.info("================================================> 쿠폰 조회 가자");

        List<AdminCouponDTO> couponList = mapper.selectAllCoupon();

        return couponList;
    }

    @Override
    public List<AdminCouponDTO> selectCondition(Map<String, String> condition) {

        log.info("===================================> 컨디션 서비스 시작 ");

        List<AdminCouponDTO> couponList = mapper.selectCondition(condition);


        return couponList;
    }

    @Override
    public List<AdminCouponDTO> selectSection(AdminCouponDTO addPlus) {

        List<AdminCouponDTO> plusCoupon = mapper.selectSection(addPlus);

        return plusCoupon;
    }

    @Override
    @Transactional
    public void deleteCoupon(List<Integer> chkbox1) {

        log.info("쿠폰 삭제 서비스 ㄱㄱ");
        int result = 0;

        for (int i = 0; i < chkbox1.size(); i++) {

            log.info("==========================================> 삭제 시작 ");
            mapper.deleteCoupon(chkbox1.get(i));

            result++;

        }
    }

    @Override
    @Transactional
    public void supplyCoupon(List<Integer> chkbox1, List<String> chkbox2) {

        log.info("==================> 쿠폰 배포 서비스 ");
        log.info("chkbox2 ===========================> chkbox2 :" + chkbox2);
        log.info("chkbox2 ===========================> chkbox1 :" + chkbox1);

        int result = 0;

        AdminUserCouponDTO user = new AdminUserCouponDTO();

        for (int i = 0; i < chkbox1.size(); i++) {

            log.info("쿠폰 반복 시작 -======> chkbox1 : " + chkbox1.get(i));

            user.setCouponNumber(chkbox1.get(i));

            log.info("========================> user 1 : " + user);
            for (int j = 0; j < chkbox2.size(); j++) {

                log.info("=================================> chkbox2 : " + chkbox2.get(i));

                if (!chkbox2.get(j).isEmpty()) {
                    log.info("아이디 반복 시작==========> chkbox2 : " + chkbox2.get(j));

                    user.setId(chkbox2.get(j));

                    log.info("==================================> user2 : " + user);

                    mapper.supplyCoupon(user);
                }
            }
            result++;


        }
    }

}