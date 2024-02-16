package com.nf.not404found.member.emailsend;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class EmailSender {
    public static void sendEmail(String to, String from, String host, String subject, String text) {
        // SMTP 서버 설정
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.naver.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        // 인증 정보 설정
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wjdwltjq7289", "Qwqw13245@");
            }
        });

        try {
            // 이메일 메시지 생성
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            System.out.println("EmailSender 클래스 : "+to);
            System.out.println("EmailSender 클래스 : "+text);
            // 메시지 전송
            Transport.send(message);
            System.out.println("이메일 전송 성공!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static boolean emailSend(String to, String pwdKey) {
        // 이메일 발신자, 수신자, SMTP 호스트 및 메시지 내용 설정 to는 수신자
        String from = "wjdwltjq7289@naver.com"; // 발신자 이메일 주소
        String host = "smtp.naver.com"; // SMTP 서버 주소
        String subject = "집꾸미 이메일 인증"; // 이메일 제목
        try{
            sendEmail(to, from, host, subject, pwdKey);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
