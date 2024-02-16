package com.nf.not404found.admin.statistics.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminStatistics {

    @GetMapping("statistics")
    public String statisticsPage(Model model){

        model.addAttribute("boardType", "통계");

        return "/admin/statistics/admin";
    }
}
