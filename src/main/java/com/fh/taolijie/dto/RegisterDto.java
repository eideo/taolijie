package com.fh.taolijie.dto;

import com.fh.taolijie.utils.Constants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Created by wynfrith on 15-6-11.
 */
public class RegisterDto {

    @Length(min = 6, max = 20, message = Constants.ErrorType.USERNAME_ILLEGAL)
    private String username;
    @Length(min = 6, max = 25, message = Constants.ErrorType.PASSWORD_ILLEGAL)
    private String password;
    @Email
    private String email;
    private String rePassword;
    private boolean isEmployer;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isEmployer() {
        return isEmployer;
    }

    public void setIsEmployer(boolean isEmployer) {
        this.isEmployer = isEmployer;
    }
}
