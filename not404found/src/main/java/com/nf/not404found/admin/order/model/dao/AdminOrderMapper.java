package com.nf.not404found.admin.order.model.dao;

import com.nf.not404found.admin.order.model.dto.AdminOrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminOrderMapper {
    List<AdminOrderDTO> selectAllOrder();
}
