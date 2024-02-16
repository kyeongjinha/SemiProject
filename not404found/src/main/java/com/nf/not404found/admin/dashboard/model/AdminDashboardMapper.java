package com.nf.not404found.admin.dashboard.model;

import com.nf.not404found.admin.dashboard.model.dto.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDashboardMapper {

    List<DashboardDTO> selectAllDashboardData();

    List<ProductStatus> selectAllDashboardProductData();

    List<AdminBoardStatusDTO> selectAllBoardStatus();

    List<AdminDashboardMemberDTO> selectAllMembersStatus();

    List<AdminDashboardStatistic> selectAllStatistic();

    @Delete("DELETE FROM board WHERE categorycode_board = 4")
    boolean resetFreeBoard();
}
