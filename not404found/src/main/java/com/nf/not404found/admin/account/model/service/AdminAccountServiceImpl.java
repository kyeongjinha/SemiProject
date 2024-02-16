package com.nf.not404found.admin.account.model.service;


import com.nf.not404found.admin.account.model.dao.AdminAccountsMapper;
import com.nf.not404found.admin.account.model.dto.AdminAccountDTO;
import com.nf.not404found.admin.account.model.dto.AdminBlackDTO;
import com.nf.not404found.admin.account.model.dto.AdminDormantDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AdminAccountServiceImpl implements AdminAccountService {

    private final AdminAccountsMapper mapper;

    public AdminAccountServiceImpl(AdminAccountsMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AdminAccountDTO> selectAllAccountsList(){  // SelectCriteria selectCriteria 이거 필요 없지 않나? 전체인데?

        List<AdminAccountDTO> accountList = mapper.selectAllAccountsList();

        log.info("");
        log.info("");
        log.info("[AdminAccountServiceImpl] 가보자가보자 {}", accountList);

        return accountList;
    }

    @Override
    public List<AdminAccountDTO> selectOneAccount(Map<String, String> searchOne){

        List<AdminAccountDTO> accountList1 = mapper.selectOneAccount(searchOne);

        log.info("");
        log.info("");
        log.info("이힣ㅎ힣힣");

        return accountList1;
    }

    @Override
    public List<AdminBlackDTO> selectOneBlackList(Map<String, String> searchBlack) {

        List<AdminBlackDTO> blackOneList = mapper.selectOneBlackList(searchBlack);
        log.info("");
        log.info("");
        log.info("잘 됬냐 ========================================================" + blackOneList);

        return blackOneList;
    }

    @Override
    public List<AdminBlackDTO> selectBlackAccount() {

        List<AdminBlackDTO> blackList = mapper.selectAllBlackList();

        log.info("");
        log.info("");
        log.info("흐에에에에-============================================================");

        return blackList;
    }

    @Override
    public List<AdminDormantDTO> selectDormantOne(Map<String, String> dormantOne) {

        log.info("꺄아아아악============================================================");
        List<AdminDormantDTO> dormantOneList = mapper.selectDormantOne(dormantOne);
        log.info("음음 그래그래 잘 왔구먼");
        return dormantOneList;
    }

    @Override
    public List<AdminDormantDTO> selectAllDormant() {
        log.info("흠 이거 언제 끝날까 ========================================================");

        List<AdminDormantDTO> dormantList = mapper.selectAllDormant();
        log.info("오류가 한번이 안난 적 이 없 네 네  치 키ㅣㄴ ===========================================" );

        return dormantList;
    }

    @Override
    public void blacked(String id) {

        int result = mapper.blacked(id);

    }

}
