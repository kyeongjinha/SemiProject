package com.nf.not404found.admin.dashboard.model;

import com.nf.not404found.admin.dashboard.model.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final AdminDashboardMapper mapper;
    public DashboardService(AdminDashboardMapper adminDashboardMapper){
        this.mapper = adminDashboardMapper;
    }
    public List<DashboardDTO> selectAllDashboardData() {
        return mapper.selectAllDashboardData();
    }
    public List<ProductStatus> selectAllDashboardProductData() {
        return mapper.selectAllDashboardProductData();
    }
    public List<AdminBoardStatusDTO> selectAllBoardStatus() {
        return mapper.selectAllBoardStatus();
    }
    public List<AdminDashboardMemberDTO> selectAllMemberStatus() {
        return mapper.selectAllMembersStatus();
    }
    public List<AdminDashboardStatistic> selectAllStatistic() {
        return mapper.selectAllStatistic();
    }
    public boolean resetFreeBoard() {
        return mapper.resetFreeBoard();
    }
}
