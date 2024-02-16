package com.nf.not404found.mypage.model.service;


import com.nf.not404found.common.functions.UserInformation;
import com.nf.not404found.mypage.model.dao.MyPageMapper;
import com.nf.not404found.mypage.model.dto.AddrDTO;
import com.nf.not404found.mypage.model.dto.MyPageCouponDTO;
import com.nf.not404found.mypage.model.dto.MyPageOrderDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyPageService {

    private final MyPageMapper mapper;
    private final UserInformation user;
    private final PasswordEncoder passwordEncoder;
    public MyPageService(MyPageMapper mapper, UserInformation user,PasswordEncoder passwordEncoder){
        this.mapper = mapper;
        this.user = user;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public boolean changePhone(String phone) {
        String id = user.getId();
        return mapper.changePhone(phone, id);
    }
    @Transactional
    public boolean changeEmail(String email) {
        String id = user.getId();
        return mapper.changeEmail(email,id);
    }
    @Transactional
    public boolean changePwd(String pwd) {

        String id = user.getId();
        String password = passwordEncoder.encode(pwd);
        System.out.println(password);
        return mapper.changePwd(password,id);
    }
    @Transactional
    public boolean insertAddr(AddrDTO addr) {
        String address = addr.getAddress();
        String zonecode = addr.getZonecode();
        String name = addr.getName();
        String addrDetail = addr.getAddrDetail();
        String id = user.getId();
        return mapper.insertAddr(address,zonecode,name,addrDetail,id);
    }

    public List<AddrDTO> selectUserAddr(String id) {
        return mapper.selectUserAddr(id);
    }

    public boolean deleteAddr(String name) {
        String id = user.getId();
        return mapper.deleteAddr(name,id);
    }

    public List<MyPageCouponDTO> selectMyCoupon() {
        String id = user.getId();
        return mapper.selectMyCoupon(id);
    }

    public List<MyPageOrderDTO> selectMyOrder() {
        String id = user.getId();
        return mapper.selectOrder(id);
    }
}
