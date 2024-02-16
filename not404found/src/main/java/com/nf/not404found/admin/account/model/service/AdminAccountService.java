package com.nf.not404found.admin.account.model.service;

import com.nf.not404found.admin.account.model.dto.AdminAccountDTO;
import com.nf.not404found.admin.account.model.dto.AdminBlackDTO;
import com.nf.not404found.admin.account.model.dto.AdminDormantDTO;

import java.util.List;
import java.util.Map;

public interface AdminAccountService {

    List<AdminAccountDTO> selectAllAccountsList();

    List<AdminAccountDTO> selectOneAccount(Map<String, String> searchOne);

    List<AdminBlackDTO> selectBlackAccount();

    List<AdminBlackDTO> selectOneBlackList(Map<String, String> searchBlack);

    List<AdminDormantDTO> selectAllDormant();

    List<AdminDormantDTO> selectDormantOne(Map<String, String> dormantOne);

    void blacked(String id);

}
