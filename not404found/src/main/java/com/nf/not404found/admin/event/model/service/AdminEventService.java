package com.nf.not404found.admin.event.model.service;

import com.nf.not404found.admin.event.model.dto.AdminEventDTO;

import java.util.List;

public interface AdminEventService {
    List<AdminEventDTO> selectAllEvent();

    void insertEvent(List<Integer> chkbox1, AdminEventDTO eventDTO);
}
