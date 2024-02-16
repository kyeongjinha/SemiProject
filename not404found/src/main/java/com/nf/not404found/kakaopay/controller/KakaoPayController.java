package com.nf.not404found.kakaopay.controller;


import com.nf.not404found.common.functions.UserInformation;
import com.nf.not404found.kakaopay.model.dto.ApproveResponse;
import com.nf.not404found.kakaopay.model.dto.ReadyResponse;
import com.nf.not404found.kakaopay.model.service.KakaoPayService;
import com.nf.not404found.payment.model.service.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@Controller
@SessionAttributes({"tid","order"})
public class KakaoPayController {


    private final KakaoPayService kakaoPayService;
    private final UserInformation user;

    private final PaymentServiceImpl paymentService;

    public KakaoPayController(KakaoPayService kakaoPayService, UserInformation user, PaymentServiceImpl paymentService) {
        this.kakaoPayService = kakaoPayService;
        this.user = user;
        this.paymentService = paymentService;
    }

    @PostMapping("/order/pay")
    @ResponseBody
    public ReadyResponse payReady(Model model,
                                  @RequestParam int totalprice,
                                  @RequestParam int mileage,
                                  @RequestParam(required = false) String buyname,
                                  @RequestParam(required = false) String buytel,
                                  @RequestParam(required = false) String buyemail,
                                  @RequestParam(required = false) String receivename,
                                  @RequestParam(required = false) String receivetel,
                                  @RequestParam(required = false) String receiveaddress,
                                  @RequestParam(required = false) String receiveaddressdetail,
                                  @RequestParam(required = false) String pname,
                                  @RequestParam(required = false) String coupon,
                                  @RequestParam(required = false) String amount,
                                  @RequestParam(required = false) String deliveryrequest,
                                  @RequestParam(required = false) String deliveryCost,
                                  @RequestParam(required = false) int productCode
//                                  @RequestParam(required = false) int totalamount
                                  ) {


        log.info("========================================================> 시작? 컨트롤 ? ");
        log.info("=======================================================> totalprice : " + totalprice);
        log.info("=======================================================> mileage : " + mileage);
        log.info("=======================================================> buyname : " + buyname);
        log.info("=======================================================> buytel : " + buytel);
        log.info("=======================================================> buyemail : " + buyemail);
        log.info("=======================================================> receivename : " + receivename);
        log.info("=======================================================> receivetel : " + receivetel);
        log.info("=======================================================> receiveaddress : " + receiveaddress);
        log.info("=======================================================> receiveaddressdetail : " + receiveaddressdetail);
        log.info("=======================================================> pname : " + pname);
        log.info("=======================================================> coupon : " + coupon);
        log.info("=======================================================> amount : " + amount);


        ReadyResponse readyResponse = kakaoPayService.payReady(totalprice,      //totalamount
                mileage,
                buyname,
                buytel,
                buyemail,
                receivename,
                receivetel,
                receiveaddress,
                pname,
                amount,
                coupon);
        // 요청처리후 받아온 결재고유 번호(tid)를 모델에 저장
        model.addAttribute("tid", readyResponse.getTid());
        log.info("결재고유 번호: " + readyResponse.getTid());

        int deliveryCode = paymentService.selectdliveryCode(receiveaddress,receiveaddressdetail);

        log.info("======================================================= 딜리버리코드");

        int couponCode = paymentService.selectCoupon(coupon);

        log.info("======================================================> 쿠폰코드 ");

        paymentService.insertOrder(user.getId(), deliveryCode, productCode, totalprice, amount,deliveryrequest, deliveryCost,couponCode);

        log.info("=========================================================컨트롤러 첫 리턴 ");



        return readyResponse; // 클라이언트에 보냄.(tid,next_redirect_pc_url이 담겨있음.)
    }

    @GetMapping("/order/pay/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken, @ModelAttribute("tid") String tid, Model model) {

        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
        log.info("결재고유 번호: " + tid);

        log.info("================================ model : " + model);

        // 카카오 결재 요청하기
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);




        log.info("----------어디까지 된거냐 여긴 컨트롤러 마지막 ");

        return "redirect:/order/orderComplete";
    }
}
