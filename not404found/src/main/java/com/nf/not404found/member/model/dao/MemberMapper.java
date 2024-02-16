package com.nf.not404found.member.model.dao;

import com.nf.not404found.member.model.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Select("SELECT COUNT(*) FROM accounts WHERE accounts.id = #{id}")
    int usernameExists(String id);

    @Insert("INSERT INTO email_check(id,pwd_key) VALUES (#{id},#{pwd_key})")
    boolean checkEmail(String id, String pwd_key);

    @Select("SELECT COUNT(*) FROM email_check WHERE id = #{id} AND pwd_key = #{pwdCode}")
    int checkCode(String id, String pwdCode);

    @Insert("INSERT INTO accounts (id,password,name,email,phone_number,regist_date,last_login,account_status)" +
            "VALUES (#{id},#{pwd},#{name},#{email},#{phone},now(),now(),'FRIEND')")
    boolean createMember(String id, String email, String pwd, String name, String phone);


    LoginUserDTO findId(String username);


}
