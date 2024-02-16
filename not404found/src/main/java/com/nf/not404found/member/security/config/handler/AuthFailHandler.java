package com.nf.not404found.member.security.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {    //이거 구현하기 하면 에러 처리 가능

    /*
     * onAuthenticationFailure 메소드가 호출될 때 defaultFailureUrl인 경우 리다이렉션을 수행하는 AuthenticationFailureHandler이다.
     * */

    /**
     * 사용자의 잘못된 로그인 시도를 커스텀하기 위한 핸들러이다.
     * @param request 사용자 요청 객체
     * @param response 서버 응답값
     * @param exception 발생한 오류를 담는 객체
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 응답할 오류 메세지를 저장하기위한 변수 선언
        String errorMessage;

        if(exception instanceof BadCredentialsException) { // BadCredentialsException 오류는 사용자의 아이디가 DB에 존재하지 않거나 비밀번호가 잘못된 경우 발생
            errorMessage = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
        } else if(exception instanceof UsernameNotFoundException) { // UsernameNotFoundException 오류는 사용자 아이디 정보를 찾을 수 없을 때 발생
            errorMessage = "존재하지 않는 이메일 입니다. \n 이메일을 확인해주세요";
        } else if(exception instanceof InternalAuthenticationServiceException) { // InternalAuthenticationServiceException 오류는 서버가 사용자 정보를 검증하는 과정에서 발생
            errorMessage = "서버에서 오류가 발생되었습니다. \n 관리자에게 문의하세요";
        } else if(exception instanceof AuthenticationCredentialsNotFoundException) {
            // AuthenticationCredentialsNotFoundException 오류는 보안 컨텍스트에 인증 객체가 존재하지 않거나 인증 정보가 없는 상태에서 보안처리된 리소스에 접근하는 경우 발생
            errorMessage = "인증 요청이 거부되었습니다. \n 관리자에게 문의해주세요";
        } else {
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. \n 관리자에게 문의해주세요";
        }
        System.out.println(errorMessage);

        // url을 안전하게 인코딩하게위해서 URLEncoder를 사용해서 지정
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        // 오류를 처리할 페이지로 이동
        setDefaultFailureUrl("/auth/fail?message=" + errorMessage);

        // 부모에 메서드를 호출하여 다음 로직을 수행하도록 진행
        super.onAuthenticationFailure(request, response, exception);
    }
}
