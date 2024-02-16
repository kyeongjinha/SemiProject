package com.nf.not404found.admin.event.model.dao;


import com.nf.not404found.admin.event.model.dto.AdminEventDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminEventMapper {

    List<AdminEventDTO> selectAllEvent();

    List<AdminEventDTO> selectOneEvent(Map<String, String> searchOne);

    void insertEvent(AdminEventDTO eventDTO);
}
