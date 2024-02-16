package com.nf.not404found.admin.order.controller;


import com.nf.not404found.admin.order.model.dto.AdminOrderDTO;
import com.nf.not404found.admin.order.model.service.AdminOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
@Slf4j
public class AdminOrderController {


    private final AdminOrderServiceImpl orderService;

    public AdminOrderController(AdminOrderServiceImpl orderService) {
        this.orderService = orderService;
    }


    @GetMapping("order")
    public ModelAndView orderPage(ModelAndView mv){

        log.info("===========================================> 주문관리 페이지 전체 조회 입장 : ");
        mv.addObject("boardType", "주문 관리");
        mv.setViewName("/admin/order/order");

        List<AdminOrderDTO> orderList = orderService.selectAllOrder();

        mv.addObject("orderList", orderList);


        return mv;
    }
}
