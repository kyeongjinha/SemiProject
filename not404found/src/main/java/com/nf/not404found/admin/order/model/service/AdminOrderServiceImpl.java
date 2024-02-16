package com.nf.not404found.admin.order.model.service;


import com.nf.not404found.admin.order.model.dao.AdminOrderMapper;
import com.nf.not404found.admin.order.model.dto.AdminOrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminOrderServiceImpl implements AdminOrderService{

    private final AdminOrderMapper mapper;

    public AdminOrderServiceImpl(AdminOrderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AdminOrderDTO> selectAllOrder() {

        log.info("============================================> 주문관리 조회 서비스단 ");

        List<AdminOrderDTO> orderList = mapper.selectAllOrder();

        log.info("=========================================> 주문 매퍼 다녀온 뒤 orderList : " + orderList);

        for (int i = 0; i < orderList.size(); i++) {

            if (orderList.get(i).getCouponList().getName() == null){

                orderList.get(i).getCouponList().setName("미사용");
                orderList.get(i).getCouponList().setRate(0);
            }

        }
        log.info("=========================================> 주문 매퍼 다녀온 뒤 orderList : " + orderList);

        return orderList;
    }
}
