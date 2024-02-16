package com.nf.not404found.member.model.service;

import com.nf.not404found.common.random.RandomString;
import com.nf.not404found.member.model.dao.EmailMapper;
import com.nf.not404found.member.model.dto.PwdFindDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.nf.not404found.common.random.RandomString.createRandomString;

@Service
public class EmailService {
    private final EmailMapper mapper;
    private Map<String, User> users = new HashMap<>();
    public EmailService(EmailMapper mapper){
        this.mapper = mapper;
    }


    @Transactional
    public int insertRandomEmailCode(String userId, String random) {
        //받아오는 userId 와 random 값 콘솔창에 출력 가능하게 해보자


            PwdFindDTO pwdFind = new PwdFindDTO();
            pwdFind.setId(userId);
            pwdFind.setPwd_key(random);
            int randomStr = mapper.insertRandomEmailCode(pwdFind);

            if(randomStr > 0 ){
                System.out.println("DB추가 : 성공" + randomStr);
            }else{
                System.out.println("DB추가 : 실패");
            }

            return pwdFind.getNum();
        }

    public boolean checkEmail(String name, String id) {

        // 넘겨받은 값이랑 데이터베이스에 있는 내용이이랑 같은지 비교
        String searchRandomStr = mapper.searchRandomStr(id);
        System.out.println("searchRandomStr = " + searchRandomStr);

        if(searchRandomStr.equals(name)) {
            System.out.println("입력한인증번호 = " + name);
            return true;
        } else{

            return false;
        }
    }
    @Transactional
    public boolean updatePassword(PwdFindDTO pwdFind) {
        System.out.println("pwdFind = " + pwdFind);
        return mapper.changePwd(pwdFind);
    }

//    public boolean changePassword(PwdFindDTO pwdFind) {
//        String userId = pwdFind.getId();
//        String newPassword = pwdFind.getPwd_key();
//        System.out.println("userId = " + userId);
//        System.out.println("newPassword = " + newPassword);
//        User user = users.get(userId);
//        if (user != null) {
//            user.setPassword(newPassword);
//
//
//            return true;
//
//        }
//
//        return false;
//    }
}
//    String userId = pwdFind.getId();
//    String newEncodedPassword = pwdFind.getPwd_key();
//    Optional<User> optionalUser = userRepository.findByUserId(userId);
//
//        if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//
//
//                user.setPassword(newEncodedPassword);
//
//                // 사용자 저장
//                userRepository.save(user);
//
//                return true; // 변경 성공
//                }
//
//                return false;
