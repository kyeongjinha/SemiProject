package com.nf.not404found.member.model.dto;

import com.nf.not404found.member.security.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter@ToString
public class LoginUserDTO {
    private String id;
    private String name;
    private String password;
    private UserRole role;

    public List<String> getRole() {

        if(this.role.getRole().length() > 0){
            return Arrays.asList(this.role.getRole().split(","));
        }

        return new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
