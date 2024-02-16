package com.nf.not404found.member.model.service;


import com.nf.not404found.member.model.dao.MemberMapper;
import com.nf.not404found.member.model.dto.LoginUserDTO;
import com.nf.not404found.member.model.dto.MemberDTO;
import com.nf.not404found.member.security.auth.model.dto.AuthDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberMapper mapper;
    public MemberService(MemberMapper mapper){
        this.mapper = mapper;
    }

    public boolean usernameExists(String username) {
        int result = mapper.usernameExists(username);
        System.out.println("쿼리 보낸 count 수 : "+result);
        boolean re = result == 0;
        return re;
    }

    /**
     * email_check 테이블에 id, pwd_code INSERT 메소드
     *
     * @param id 사용자가 회원 가입 시 입력한 필드의 아이디
     * @param key 사용자의 아이디와 함께 테이블에 들어갈 인증 코드
     * @return : 잘 됐는지 체크 (true or false)
     */
    @Transactional  //커밋 용
    public boolean checkEmail(String id,String key) {   //아이디, 랜덤 키 갖고
        boolean result = mapper.checkEmail(id, key);    //매퍼로 ㄱㄱ
        System.out.println(result);                     //잘 됐는지 출력 용
        return result;
    }

    public int checkCode(String id, String pwdCode) {
        int result = mapper.checkCode(id,pwdCode);
        System.out.println("사용자가 입력한 pwdCode와 id가 일치하는 테이블은 몇개?? : "+result);
        return result;
    }

    @Transactional
    public boolean createMember(MemberDTO member) {
        System.out.println(member.getId());
        return mapper.createMember(member.getId(),member.getEmail(),member.getPwd(),member.getName(),member.getPhone());
    }
    public AuthDetails findByUserId(String username) {
       // mapper.findId(username);
        LoginUserDTO login = mapper.findId(username);
        AuthDetails authDetails = new AuthDetails(login);
        return authDetails;
    }
}
