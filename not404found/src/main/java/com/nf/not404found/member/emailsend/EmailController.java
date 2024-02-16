package com.nf.not404found.member.emailsend;

import com.nf.not404found.common.functions.UserInformation;
import com.nf.not404found.common.random.RandomString;
import com.nf.not404found.member.model.dao.MemberMapper;
import com.nf.not404found.member.model.dto.PwdFindDTO;
import com.nf.not404found.member.model.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller     //나는 컨트롤러야 라고 지정해주는 어노테이션
@RequestMapping("/member")      //("/member") 경로로 시작하는 모든 웹 요청을 이 클래스의 메서드로 매핑할거야
public class EmailController {      //EmailController 클래스시작{

    private final EmailService service;        //EmailService 타입의 emailService 라는 private final 필드를 선언
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 하기위해서
    private final MemberMapper mapper;
    private final UserInformation user;
    public EmailController(EmailService service, PasswordEncoder passwordEncoder, MemberMapper mapper, UserInformation user) {     //EmailController2 클래스의 생성자선언, EmailService 타입의 emailService2를 받는다 //EmailService를 매개변수로 받아 컨트롤러에 주입 //이것은 클래스가 외부에서 EmailService의 객체를 필요로 하며, 이 객체는 외부에서 생성되어 주입됩니다
        this.service = service;
        this.passwordEncoder = passwordEncoder;

        this.mapper = mapper;
        this.user = user;
    }
    //EmailController 클래스는 EmailService 객체를 필요로 하며, 이 객체는 외부에서 생성되어 전달되는 구조

    @RequestMapping("/send-email-form")
    public String sendEmailForm() {
        return "/member/pwdFind";
    }
    // 직접 url을 불르는건 가능하긴 하지만 스프링과 톰캣서버 에서는?
    // @RequestMapping과 같은 메커니즘을 사용해서 요청하는것이 일반적
    // @RequestMapping으로 한번 거쳐서 요청해야한다?
    // 유지보수와 확장을 쉽게 만든다, URL 구조를 변경하지 않고 컨트롤러 메서드를 수정 교체가능
    @RequestMapping("/send-email-form2")
    public String sendEmailForm2() {

        user.getEmail();
        return "/member/emailCertification";
    }
    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String userId, @RequestParam String email, Model model) {

        System.out.println("email = " + email);
        System.out.println("userId = " + userId);
        try {
            String subject = "집꾸미 이메일 인증";
            String text = "Your verification code is: " + generateVerificationCode();

            String random = RandomString.createRandomString();
            boolean result = EmailSender.emailSend(email, random);
            System.out.println("result = " + result);

            // 메일보내기가 성공을 하면 데이터베이스에 업데이트
            if(result) {
             int resultId =  service.insertRandomEmailCode(userId,random);
                PwdFindDTO pwdFind = new PwdFindDTO();
                pwdFind.setNum(resultId);
                System.out.println("resultId = " + resultId);
                model.addAttribute("type", resultId);
                model.addAttribute("email", email);
            }

        } catch (Exception e) {
            e.printStackTrace();
       }
        return "member/pwdFindCertification";

    }

   @PostMapping("send-check")
   public String checkEmail(@RequestParam String name, @RequestParam(required = false) String id, @RequestParam(required = false) String email,  Model model){
       System.out.println("name = " + name);
       System.out.println("id = " + id);

       boolean userExists = service.checkEmail(name, id);    //name 값과 id 값을 가져왔으니 db에 저장된 name와 id 값을 비교후 같을시
                                                                             //리턴으로 비밀번호 변경페이지로 이동
       if(userExists){
           //실패시 에러 메세지 재입력 하세요 출력
           model.addAttribute("id", id);
           model.addAttribute("email", email);
           model.addAttribute("name", name);
//           model.addAttribute("email", (String)model.getAttribute("email"));
           return "member/pwdChange";
       }else{
           model.addAttribute("error", name);
           return "member/pwdFindFail"; // 에러페이지 추가해서 적용
       }
   }

   // 사용자가 입력한 비밀번호를 입력 받아서
    @PostMapping("pwdChange")
    public String changePassword(Model model, @RequestParam String changePwd, @RequestParam String email){

        System.out.println("email : " + email );
        System.out.println("changePwd = " + changePwd);
        System.out.println("encode = " + passwordEncoder.encode(changePwd));

        PwdFindDTO pwdFind = new PwdFindDTO();
        pwdFind.setId(email);
        pwdFind.setPassword(passwordEncoder.encode(changePwd));

        boolean changePasswordSuccess = service.updatePassword(pwdFind);

        if(changePasswordSuccess){
            model.addAttribute("successMessage", "비밀번호 변경이 완료되었습니다.");
            return "member/login";
        }else{
            model.addAttribute("errorMessage", "비밀번호 변경에 실패하였습니다.");
            return "member/pwdFind"; // 성공시 /실패시 이동할 페이지 설정
        }

    }


    private String generateVerificationCode() {
        return "123456"; // For example, a simple static code
    }


    }
