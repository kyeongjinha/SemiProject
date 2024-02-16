package com.nf.not404found.order.model.service;


import com.nf.not404found.order.model.dao.OrderMapper;
import com.nf.not404found.order.model.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService{

    private final OrderMapper mapper;
    public OrderService(OrderMapper mapper) {
        this.mapper = mapper;
    }

    public OrderDTO getOrderInfor(String id) {
        return mapper.getOrderInfor(id);
    }

    public int getCouponDiscountRate(String coupon) {
        return mapper.getCouponDiscountRate(coupon);
    }

    public List<OrderDTO> orderInfo() {

        List<OrderDTO> orderList = mapper.getorderInfo();

        return orderList;
    }
}
