package com.nf.not404found.admin.coupon.controller;


import com.nf.not404found.admin.account.model.dto.AdminAccountDTO;
import com.nf.not404found.admin.account.model.service.AdminAccountServiceImpl;
import com.nf.not404found.admin.common.exception.CouponInsertException;
import com.nf.not404found.admin.coupon.model.dto.AdminCouponDTO;
import com.nf.not404found.admin.coupon.model.service.AdminCouponServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/*")
@Slf4j
public class AdminCouponController {

    private final AdminCouponServiceImpl couponService;
    private final AdminAccountServiceImpl accountService;

    public AdminCouponController(AdminCouponServiceImpl couponService, AdminAccountServiceImpl accountService) {
        this.couponService = couponService;
        this.accountService = accountService;
    }

    @GetMapping("coupon")
    public ModelAndView couponPage(ModelAndView mv,
                                   @RequestParam(required = false) String searchCondition,
                                   @RequestParam(required = false) String searchCoupon,
                                   @RequestParam(required = false) String searchRates,
                                   @RequestParam(required = false) String searchRatee,
                                   @RequestParam(required = false) String periods,
                                   @RequestParam(required = false) String periode){

        log.info("===============================================================> searchCondition : " + searchCondition);
        log.info("===============================================================> searchCoupon : " + searchCoupon);
        log.info("===============================================================> periods : " + periods);
        log.info("===============================================================> periode : " + periode);
        log.info("===============================================================> searchRates : " + searchRates);
        log.info("===============================================================> searchRatee : " + searchRatee);

        mv.addObject("boardType", "쿠폰");
        mv.setViewName("admin/coupon/admin");

        Map<String, String> condition = new HashMap<>();

        List<AdminCouponDTO> couponList = new ArrayList<>();

        AdminCouponDTO addPlus = new AdminCouponDTO();


        int count = 0;

//        if (periods != 0 || periode != 0 || searchRates != 0 || searchRatee != 0){
//
//            log.info("===============================================================> 조건문 시작 number ");
//
//            addPlus.setPeriods(periods);
//            addPlus.setPeriode(periode);
//            addPlus.setSearchRates(searchRates);
//            addPlus.setSearchRatee(searchRatee);
//
//            couponList = couponService.selectSection(addPlus);
//
//            log.info("========================================================= > addPlus " + addPlus);
//
//            couponList.add(addPlus);
//
//        }

        if (searchCoupon != "" && searchCoupon != null || periods != null || periode != null || searchRates != null || searchRatee != null){


            log.info("===============================================================> 조건문 시작 search ");
            condition.put("searchCondition", searchCondition);
            condition.put("searchCoupon", searchCoupon);
            condition.put("searchRates", searchRates);
            condition.put("searchRatee", searchRatee);
            condition.put("periode", periode);
            condition.put("periods", periods);


            couponList = couponService.selectCondition(condition);

            count += couponList.size();



        }
        else{

            log.info("===============================================================> searchCondition : " + searchCondition);
            log.info("===============================================================> searchCoupon : " + searchCoupon);
            log.info("===============================================================> periods : " + periods);
            log.info("===============================================================> periode : " + periode);
            log.info("===============================================================> searchRates : " + searchRates);
            log.info("===============================================================> searchRatee : " + searchRatee);
            log.info("===============================================================> 전체 조회 ");
            couponList = couponService.selectAllCoupon();


            log.info("=================================================> 쿠폰 갯수 : " + couponList.size());
            count += couponList.size();

            mv.addObject("couponList", couponList);


            log.info("============================================> 쿠폰리스트 = " + couponList);
        }

        log.info("===============================================================> condition : " + couponList);
//        ("".equals(searchCoupon) || searchCoupon == null && periods == null && periode == null && searchRates == null && searchRatee == null){

        mv.addObject("couponList", couponList);
        mv.addObject("count", count);

        return mv;
    }

    @PostMapping("coupon/search")
    public String couponSearchPage(@ModelAttribute AdminCouponDTO coupon,
                                   @RequestParam(required = false) List<Integer> chkbox1,
                                   @RequestParam(required = false) List<String> chkbox2,
                                   @RequestParam(required = false) String deleteBtn,
                                   @RequestParam(required = false) String supplyBtn,
                                   RedirectAttributes rttr){

// 아이디 정보 받아오기 확인 ,
        log.info("=================================================================>");
        log.info("=========================================================> 쿠폰 서치로 왔나 ");
        log.info("===============================================================> chkbox1 : " + chkbox1);
        log.info("===============================================================> chkbox2 : " + chkbox2);
        log.info("===============================================================> supplyBtn : " + supplyBtn);
        log.info("===============================================================> deleteBtn : " + deleteBtn);
        log.info("===============================================================> coupon : " + coupon);

        if (deleteBtn != null) {
            // 선택 삭제 누를 때
            log.info("============================================= 선택 삳ㄱ제 ");
            couponService.deleteCoupon(chkbox1);

        } else if (supplyBtn != null ) {
            // 지급완료 누를 때


            couponService.supplyCoupon(chkbox1, chkbox2);
            log.info("======================================================> 지급완료 ");

        }


        return "redirect:/admin/coupon";
    }

    @GetMapping("coupon/insertCoupon")
    public String couponPageInsert(Model model){

        model.addAttribute("boardType", "쿠폰");

        return "/admin/coupon/insertCoupon";
    }

    @PostMapping("coupon/insertCoupon")
    public String couponInsert(@ModelAttribute AdminCouponDTO coupon,
                               RedirectAttributes rttr) throws CouponInsertException {

        log.info("-================================================== 쿠폰 등록 시작 ");

        couponService.insertCoupon(coupon);

        rttr.addFlashAttribute("complete", "쿠폰 등록에 성공하셨습니다.");

        // 복구용 메인에 커밋만들기


        return "redirect:/admin/coupon";
    }

    @GetMapping(value = "coupon/data", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<AdminAccountDTO> couponMember(){

        log.info("===================================================== 쿠폰 비동기로 멤버 가져오기 ======");


        List<AdminAccountDTO> memberList = accountService.selectAllAccountsList();

        log.info("=================================================> 잘 온건지 확인 쿠폰 회원정보 = " + memberList);
        log.info("=================================================> 잘 온건지 확인 쿠폰 회원정보 : " + memberList.size());

        return memberList;
    }
}
