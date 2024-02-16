package com.nf.not404found.member.security.common;


public enum UserRole {
//    ADMIN("ROLE_ADMIN"),
//    USER("ROLE_USER");
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }


    public String getRole() {
        System.out.println("여기가 null 이래요");
        return role;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
