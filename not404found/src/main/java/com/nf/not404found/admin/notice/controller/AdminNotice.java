package com.nf.not404found.admin.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminNotice {

//    @GetMapping("notice")
//    public String noticePage(Model model){
//
//        model.addAttribute("boardType", "게시판 관리");
//
//        return "/admin/notice/announcement_list";
//    }
//
//    @GetMapping("notice/FAQ_list")
//    public String noticeFAQPage(Model model){
//
//        model.addAttribute("boardType", "게시판 관리");
//        return "/admin/notice/FAQ_list";
//    }
//
//    @GetMapping("notice/QnA_list")
//    public String noticeQnAPage(Model model){
//
//        model.addAttribute("boardType", "게시판 관리");
//
//        return "/admin/notice/QnA_list";
//    }
}
