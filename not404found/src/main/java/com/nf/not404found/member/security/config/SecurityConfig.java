package com.nf.not404found.member.security.config;

//import com.ohgiraffers.thymeleafspringboot.config.handler.AuthFailHandler;

import com.nf.not404found.member.security.config.handler.AuthFailHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthFailHandler authFailHandler;

    public SecurityConfig(AuthFailHandler authFailHandler) {
        this.authFailHandler = authFailHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적 리소스 설정 제외 처리
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http// 페이지 권한 설정
                .authorizeHttpRequests(auth ->{
                    //auth.requestMatchers("/notice/*", "/board/*", "/thumbnail/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
                    auth.requestMatchers("/admin/*").hasAnyAuthority("ROLE_ADMIN"); //admin/dashboard는 ROLE_ADMIN 만 가능
                    auth.requestMatchers("/member/login").permitAll();
                    auth.requestMatchers("/member/**").permitAll();
                    auth.requestMatchers("/productPage/").permitAll();
                    auth.requestMatchers("/imagePath/**").permitAll();
                    //auth.requestMatchers("productpage/productPage").permitAll();
                    auth.requestMatchers("product/**").permitAll();
                    auth.requestMatchers("/mypage/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
                    auth.requestMatchers("/").permitAll();
                    //auth.requestMatchers("/**").permitAll();  // 모든 리소스를 권한 없이 사용가능
                    auth.anyRequest().authenticated();
                })
                // 로그인 설정
                .formLogin(login -> {
                    login.loginPage("/member/login");   //커스텀 로그인 페이지 사용
                    login.usernameParameter("memberId"); // 사용자 id 입력 필드 (input의 name과 일치)
                    login.passwordParameter("memberPwd"); // 사용자 pass 입력 필드 (input의 name과 일치)
                    login.defaultSuccessUrl("/");  //로그인 성공시 이동 페이지
                    login.failureHandler(authFailHandler); // auth
                })
                // 로그아웃 설정
                .logout(logout ->{
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")); // 로그아웃 요청 url
                    logout.deleteCookies("JSESSIONID"); // 로그아웃 시 사용자의 JSESSIONID 삭제
                    logout.invalidateHttpSession(true); // 서버 세션 소멸처리
                    logout.logoutSuccessUrl("/"); // 로그아웃 성공시 이동할 페이지
                })
                // 세션설정
                .sessionManagement(session ->{
                    session.maximumSessions(1); // 세션 개수 제한
                    session.invalidSessionUrl("/"); // 세션 만료시 이동할 url
                })
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}
