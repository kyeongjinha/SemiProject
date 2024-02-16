package com.nf.not404found.admin.account.controller;


import com.nf.not404found.admin.account.model.dto.AdminAccountDTO;
import com.nf.not404found.admin.account.model.dto.AdminBlackDTO;
import com.nf.not404found.admin.account.model.dto.AdminDormantDTO;
import com.nf.not404found.admin.account.model.service.AdminAccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/*")
@Slf4j
public class AdminAccountController {


//    @GetMapping("member")
//    public String memberPage(Model model){
//
//        model.addAttribute("boardType", "회원 관리");
//
//
//        return "/admin/member/admin";
//    }

    private final AdminAccountServiceImpl accountService;
    public AdminAccountController(AdminAccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "member")
    public ModelAndView selectAllAccounts(@RequestParam(required = false) String searchCondition,   //입력한 값(selectOption)
                                          @RequestParam(required = false) String searchValue,       //입력한 값(inputText)
                                          ModelAndView mv){

        mv.addObject("boardType", "회원 관리");

        log.info("");
        log.info("");
        log.info("[계정간다]==================================================>");

        List<AdminAccountDTO> searchList = null;

        if(searchCondition != null || searchValue != null) {
            log.info(" ");
            log.info("하나고르는거 가냐 ================> { } start");

            Map<String, String> searchOne = new HashMap<>();

            searchOne.put("searchCondition", searchCondition);
            searchOne.put("searchValue", searchValue);

            log.info("검색 잘 되냐=============================>" + searchOne);

            searchList = accountService.selectOneAccount(searchOne);

            log.info("잘 들어갔냐----------------------------------> " + searchList);

        }

        List<AdminAccountDTO> accountList = accountService.selectAllAccountsList();

        log.info("잘들어왔냐? accountList = " + accountList );

        mv.addObject("searchList", searchList);
        mv.addObject("accountList", accountList);

        mv.setViewName("admin/member/admin");

        log.info("앧옵젵 외않되?");

        return mv;
    }
    @GetMapping("member/blacked")
    public ModelAndView memberBlackedPage(@RequestParam(required = false) String searchCondition,
                                    @RequestParam(required = false) String searchValue,
                                    ModelAndView mv){
        mv.addObject("boardType", "회원 관리");
        List<AdminBlackDTO> searchBlackList = null;

        if(searchCondition != null && searchValue != null) {
            log.info(" ");
            log.info("블랙 하나 가냐 ================> { } start");

            Map<String, String> searchBlack = new HashMap<>();

            searchBlack.put("searchCondition", searchCondition);
            searchBlack.put("searchValue", searchValue);

            log.info("조건 잘 들어갔냐 =============================>" + searchBlack);

            searchBlackList = accountService.selectOneBlackList(searchBlack);

            log.info("블랙 잘 돼?----------------------------------> " + searchBlack);
        }

        List<AdminBlackDTO> blackList = accountService.selectBlackAccount();

        log.info("블랙리스트 전체 잘 되?돼?외?참외? =============================================================");

        mv.addObject("searchBlackList", searchBlackList);
        mv.addObject("blackList", blackList);

        mv.setViewName("admin/member/blacked");


        return mv;
    }

    @GetMapping("member/blacked2")
    public String memberblacked(@RequestParam String id,
                                @RequestParam String reason){

        accountService.blacked(id);
        log.info("============================> 됬냐? ");

        return "/admin/member/blacked";
    }

    @GetMapping("member/dormant")
    public ModelAndView memberDormantPage(@RequestParam(required = false) String searchCondition,
                                    @RequestParam(required = false) String searchValue,
                                    ModelAndView mv){
        log.info("");
        log.info("");
        log.info("휴면 간다-=======================================================");
        mv.addObject("boardType", "회원 관리");

        List<AdminDormantDTO> dormantOneList = null;

        if (searchCondition != null && searchValue != null){
            log.info("혼자왔어?=======================================================================");

            Map<String, String> dormantOne = new HashMap<>();

            dormantOne.put("searchCondition", searchCondition);
            dormantOne.put("searchValue", searchValue);

            dormantOneList = accountService.selectDormantOne(dormantOne);
            log.info("잘 들어갔어?=============================================> " + dormantOneList);
        }

        List<AdminDormantDTO> dormantList = accountService.selectAllDormant();
        log.info("잘 지내..?================================================> dormantList = " + dormantList);

        mv.addObject("dormantList",dormantList);
        mv.addObject("dormantOneList", dormantOneList);

        mv.setViewName("admin/member/dormant");

        return mv;
    }

}



