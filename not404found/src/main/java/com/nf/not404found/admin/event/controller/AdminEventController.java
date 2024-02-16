package com.nf.not404found.admin.event.controller;


import com.nf.not404found.admin.event.model.dto.AdminEventDTO;
import com.nf.not404found.admin.event.model.service.AdminEventServiceImpl;
import com.nf.not404found.admin.product.model.dto.AdminProductDTO;
import com.nf.not404found.admin.product.model.service.AdminProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/admin/*")
@Slf4j
public class AdminEventController {

    private final AdminEventServiceImpl eventService;

    private final AdminProductServiceImpl productService;

    public AdminEventController(AdminEventServiceImpl eventService, AdminProductServiceImpl productService) {
        this.eventService = eventService;
        this.productService = productService;
    }


    @GetMapping("event")
    public ModelAndView eventPage(ModelAndView mv,
                                  @RequestParam(required = false) String searchCondition,
                                  @RequestParam(required = false) String searchValue){

        mv.addObject("boardType", "이벤트");
        mv.setViewName("/admin/event/admin");

        if (searchValue != null ){

            Map<String, String> searchOne = new HashMap<>();
            searchOne.put("searchCondition", searchCondition);
            searchOne.put("searchValue", searchValue);

            List<AdminEventDTO> eventList = eventService.selectOneEvent(searchOne);

            mv.addObject("eventList", eventList);
        }else {

            List<AdminEventDTO> eventList = eventService.selectAllEvent();



            mv.addObject("eventList", eventList);

        }



        return mv;
    }

    @GetMapping("event/insert")
    public String eventPage2(Model model,
                             @RequestParam(required = false) String searchValue){
        model.addAttribute("boardType", "이벤트");

        if (searchValue != null ){

            log.info("===============================> searchValue = " + searchValue);
            Map<String, String> searchOne = new HashMap<>();

            searchOne.put("searchValue", searchValue);

            List<AdminProductDTO> productList = productService.selectOneProduct(searchValue);

            model.addAttribute("productList", productList);



        } else {

        List<AdminProductDTO> productList = productService.selectAllProduct();

        model.addAttribute("productList", productList);

        }



        return "/admin/event/insert";
    }


    @PostMapping("event/insert")
    public String eventInsertPage(@ModelAttribute AdminEventDTO event,
                                  @RequestParam List<Integer> chkbox1,
                                  @RequestParam String eventName,
                                  @RequestParam String eventRate,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate
                                  ){

        log.info("==================================> 이벤트 등록 값 확인 chkbox1 = " + chkbox1);
        log.info("==================================> 이벤트 등록 값 확인 eventName = " + eventName);
        log.info("==================================> 이벤트 등록 값 확인 eventRate = " + eventRate);
        log.info("==================================> 이벤트 등록 값 확인 startDate = " + startDate);
        log.info("==================================> 이벤트 등록 값 확인 endDate = " + endDate);



        AdminEventDTO eventDTO = new AdminEventDTO();

        eventDTO.setName(eventName);
        eventDTO.setDiscountRate(Integer.parseInt(eventRate));
        eventDTO.setStartDate(startDate);
        eventDTO.setEndDate(endDate);
        eventDTO.setStatus("Y");

        log.info("=================================<> eventDTO 확인 : " + eventDTO);

        eventService.insertEvent(chkbox1, eventDTO);


        return "redirect:/admin/event/insert";

    }
}





