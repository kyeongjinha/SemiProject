package com.nf.not404found.admin.dashboard.controller;

import com.nf.not404found.admin.dashboard.model.DashboardService;
import com.nf.not404found.admin.dashboard.model.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
public class AdminDashboard {

    private final DashboardService dashboardService;
    public AdminDashboard(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    /**
     * Dashboard페이지 이동 메소드
     */
    @GetMapping("dashboard")
    public ModelAndView dashboardPage(ModelAndView mv){
        List<DashboardDTO> adminDashboardData = dashboardService.selectAllDashboardData();
        List<ProductStatus> adminProductStatus = dashboardService.selectAllDashboardProductData();
        List<AdminBoardStatusDTO> adminBoardStatus = dashboardService.selectAllBoardStatus();
        List<AdminDashboardMemberDTO> membersStatus = dashboardService.selectAllMemberStatus();
        List<AdminDashboardStatistic> adminStatistic = dashboardService.selectAllStatistic();

        mv.addObject("adminDashboardData",adminDashboardData);
        mv.addObject("adminProductStatus",adminProductStatus);
        mv.addObject("adminBoardStatus",adminBoardStatus);
        mv.addObject("membersStatus",membersStatus);
        mv.addObject("adminStatistic",adminStatistic);

        System.out.println(mv.getModel().get("adminProductStatus"));
        mv.setViewName("/admin/dashboard/admin");
        return mv;
    }
    @GetMapping("/resetFreeBoard")
    @ResponseBody
    public boolean resetFreeBoard(){
        return dashboardService.resetFreeBoard();
    }
}
