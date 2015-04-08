package com.fh.taolijie.controller.dto;

import com.fh.taolijie.utils.Constants;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

/**
 * 此类为所有用户Dto类的父类，包含了所有用户都应该具有的field
 */
public class GeneralMemberDto {
    private Integer id;

    @NotEmpty(message = Constants.ErrorType.USERNAME_ILLEGAL)
    @Length(min = 6,max =25,message = Constants.ErrorType.USERNAME_ILLEGAL)
    private String username;

    @NotEmpty(message = Constants.ErrorType.PASSWORD_ILLEGAL)
    @Length(min = 6,max =25,message = Constants.ErrorType.PASSWORD_ILLEGAL)
    private String password;

    @NotEmpty(message = Constants.ErrorType.EMAIL_ILLEGAL)
    @Length(min = 6,max =30,message = Constants.ErrorType.EMAIL_ILLEGAL)
    private String email;

    private String name;

    //private String likedIds;

    //private String studentId;
    private String gender;
    private String verified;
    private String profilePhotoPath;
    private String phone;
    private String qq;
    private Integer age;
    //private String companyName;
    //private String blockList;

    private Date created_time;
    private Boolean valid;

    private Integer complaint;
    private String favoriteJobIds;
    private String favoriteShIds;
    private String favoriteResumeIds;
    private String autoLoginIdentifier;

    private Integer profilePhotoId;

    /**
     * 保存了{@link com.sun.javafx.scene.accessibility.Role}实体的主键.
     * <p> 这些{@code Role}实体都会被关联到{@code MemberEntity}中
     */
    private List<Integer> roleIdList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*public String getLikedIds() {
        return likedIds;
    }

    public void setLikedIds(String likedIds) {
        this.likedIds = likedIds;
    }*/

    public String getAutoLoginIdentifier() {
        return autoLoginIdentifier;
    }

    public void setAutoLoginIdentifier(String autoLoginIdentifier) {
        this.autoLoginIdentifier = autoLoginIdentifier;
    }

    public String getFavoriteResumeIds() {
        return favoriteResumeIds;
    }

    public void setFavoriteResumeIds(String favoriteResumeIds) {
        this.favoriteResumeIds = favoriteResumeIds;
    }

    public String getFavoriteJobIds() {
        return favoriteJobIds;
    }

    public void setFavoriteJobIds(String favoriteJobIds) {
        this.favoriteJobIds = favoriteJobIds;
    }

    public String getFavoriteShIds() {
        return favoriteShIds;
    }

    public void setFavoriteShIds(String favoriteShIds) {
        this.favoriteShIds = favoriteShIds;
    }

    public Integer getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(Integer profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Integer getComplaint() {
        return complaint;
    }

    public void setComplaint(Integer complaint) {
        this.complaint = complaint;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public void setProfilePhotoPath(String profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
