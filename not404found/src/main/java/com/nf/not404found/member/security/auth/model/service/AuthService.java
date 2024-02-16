package com.nf.not404found.member.security.auth.model.service;

import com.nf.not404found.member.model.service.MemberService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {    //유저의 정보를 가져오는 인터페이서

    private final MemberService memberService;

    public AuthService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //얘는 UserDetails로 리턴한다
        return memberService.findByUserId(username);                                              //쿼리문으로 아이디 찾아서 그거 리턴한다.
        // 이 메소드를 어디서 사용해서 어떻게 UserDetail 로 가는 것인가..?
    }
}
