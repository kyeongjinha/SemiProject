package com.nf.not404found.admin.order.model.service;

import com.nf.not404found.admin.order.model.dto.AdminOrderDTO;

import java.util.List;

public interface AdminOrderService {
    List<AdminOrderDTO> selectAllOrder();
}
