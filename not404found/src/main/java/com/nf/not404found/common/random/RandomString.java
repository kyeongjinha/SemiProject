package com.nf.not404found.common.random;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.Character.toUpperCase;
@Component
public class RandomString {
    public static String createRandomString(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";

        for (int i = 0; i < 5; i++) {
            if (random.nextBoolean()) {
                // 알파벳 문자 추가
                int index = random.nextInt(alphabet.length());
                sb.append(toUpperCase(alphabet.charAt(index)));
            } else {
                // 숫자 추가
                int index = random.nextInt(digits.length());
                sb.append(digits.charAt(index));
            }
        }

        String randomString = sb.toString();
        System.out.println("랜덤 문자열: " + randomString);
        return randomString;
    }
}
