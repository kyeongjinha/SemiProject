package com.nf.not404found.admin.event.model.service;


import com.nf.not404found.admin.event.model.dao.AdminEventMapper;
import com.nf.not404found.admin.event.model.dto.AdminEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class AdminEventServiceImpl implements AdminEventService{

    private final AdminEventMapper mapper;

    public AdminEventServiceImpl(AdminEventMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AdminEventDTO> selectAllEvent() {
        log.info("===========================> 이벤트 조회 서비스 ");

        List<AdminEventDTO> eventList = mapper.selectAllEvent();

        log.info("============================> 매퍼 나온 이벤트 : " + eventList);

        return eventList;
    }

    public List<AdminEventDTO> selectOneEvent(Map<String, String> searchOne) {

        log.info("=======================================> 이벤트 검색 ");

        List<AdminEventDTO> eventList = mapper.selectOneEvent(searchOne);

        log.info("===========================================> 검색된 이벤트 : " + eventList);

        return eventList;
    }


    @Override
    @Transactional
    public void insertEvent(List<Integer> chkbox1, AdminEventDTO eventDTO) {

        log.info("=================================> 이벤트 등록 서비스 ");
        log.info("====================================== chkbox1 : " + chkbox1);
        log.info("=====================================> eventDTO : " + eventDTO);

        int result = 0;

        for (int i = 0; i < chkbox1.size(); i++) {

            if (chkbox1.get(i) != null) {
                eventDTO.setProductCode(chkbox1.get(i));

                mapper.insertEvent(eventDTO);

                result++;
            }
        }
    }
}
