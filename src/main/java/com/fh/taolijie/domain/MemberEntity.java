package com.fh.taolijie.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by wanghongfei on 15-3-4.
 */
/*@SqlResultSetMapping(
        name = "ResumeAndJobCategoryMapping",
        classes = @ConstructorResult(
                        targetClass = ResumeAndJobCategory.class,
                        columns = {
                                @ColumnResult(name = "resumeId", type = Integer.class),
                                @ColumnResult(name = "categoryId", type = Integer.class)
                        }
                )
)*/


@Entity
@Table(name = "member")

@NamedQueries({
        // 查询用户名是否存在
        @NamedQuery(name = "memberEntity.isExisted",
                query = "SELECT COUNT(mem) FROM MemberEntity mem " +
                        "WHERE mem.username = :username"),

        @NamedQuery(name = "memberEntity.findByIdentifier",
                query = "SELECT mem FROM MemberEntity mem " +
                        "WHERE mem.autoLoginIdentifier = :identifier"),

        // 根据用户名查找member实体
        @NamedQuery(name = "memberEntity.findMemberByUsername",
                query = "SELECT m " +
                        "FROM MemberEntity m " +
                        //"JOIN FETCH m.memberRoleCollection " +
                        "WHERE m.username = :username"),

        @NamedQuery(name = "memberEntity.findAll",
                query = "SELECT m FROM MemberEntity m "),

        @NamedQuery(name = "memberEntity.amount",
                query = "SELECT COUNT(m) FROM MemberEntity m")
})
public class MemberEntity {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String studentId;
    private String gender;
    private String verified;
    private String profilePhotoPath;
    private String phone;
    private String qq;
    private Integer age;
    private String companyName;
    private String blockList;

    private Date createdTime;
    private Boolean valid;
    private String appliedJobIds;

    private Integer complaint;
    private String likedJobIds;
    private String likedShIds;
    private String favoriteJobIds;
    private String favoriteShIds;
    private String favoriteResumeIds;
    private String autoLoginIdentifier;

/*    private String likedIds;
    private String dislikedIds;*/

    private Integer profilePhotoId;

    private Collection<EducationExperienceEntity> educationExperienceCollection;
    private Collection<JobPostEntity> jobPostCollection;
    private Collection<MemberRoleEntity> memberRoleCollection;
    private Collection<NewsEntity> newsCollection;
    private Collection<NotificationEntity> notificationCollection;
    private Collection<ResumeEntity> resumeCollection;
    private Collection<ReviewEntity> reviewCollection;
    private Collection<SecondHandPostEntity> secondHandPostCollection;

    private List<ReviewEntity> replyList;

    public MemberEntity() {

    }

    public MemberEntity(String username, String password, String email, String name, String studentId,
                        String gender, String verified, String profilePhotoPath, String phone, String qq,
                        Integer age, String companyName, String blockList, Boolean valid, Date createdTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.studentId = studentId;
        this.gender = gender;
        this.verified = verified;
        this.profilePhotoPath = profilePhotoPath;
        this.phone = phone;
        this.qq = qq;
        this.age = age;
        this.companyName = companyName;
        this.blockList = blockList;

        this.valid = valid;
        this.createdTime = createdTime;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "auto_login_identifier")
    public String getAutoLoginIdentifier() {
        return autoLoginIdentifier;
    }

    public void setAutoLoginIdentifier(String autoLoginIdentifier) {
        this.autoLoginIdentifier = autoLoginIdentifier;
    }

    @Column(name = "applied_job_ids", columnDefinition = "TEXT")
    public String getAppliedJobIds() {
        return appliedJobIds;
    }

    public void setAppliedJobIds(String appliedJobIds) {
        this.appliedJobIds = appliedJobIds;
    }

    @Column(name = "favorite_resume_ids")
    public String getFavoriteResumeIds() {
        return favoriteResumeIds;
    }

    public void setFavoriteResumeIds(String favoriteResumeIds) {
        this.favoriteResumeIds = favoriteResumeIds;
    }

    @Column(name = "favorite_sh_ids")
    public String getFavoriteShIds() {
        return favoriteShIds;
    }

    public void setFavoriteShIds(String favoriteShIds) {
        this.favoriteShIds = favoriteShIds;
    }

    @Column(name = "profile_photo_id")
    public Integer getProfilePhotoId() {
        return profilePhotoId;
    }

    public void setProfilePhotoId(Integer profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }

    @Column(name = "favorite_job_ids")
    public String getFavoriteJobIds() {
        return favoriteJobIds;
    }

    public void setFavoriteJobIds(String favoriteJobIds) {
        this.favoriteJobIds = favoriteJobIds;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "liked_job_ids", columnDefinition = "TEXT")
    public String getLikedJobIds() {
        return likedJobIds;
    }

    public void setLikedJobIds(String likedJobIds) {
        this.likedJobIds = likedJobIds;
    }

    @Column(name = "liked_sh_ids", columnDefinition = "TEXT")
    public String getLikedShIds() {
        return likedShIds;
    }

    public void setLikedShIds(String likedShIds) {
        this.likedShIds = likedShIds;
    }

    /* @Column(name = "disliked_ids", columnDefinition = "TEXT")
    public String getDislikedIds() {
        return dislikedIds;
    }

    public void setDislikedIds(String dislikedIds) {
        this.dislikedIds = dislikedIds;
    }

    @Column(name = "liked_ids", columnDefinition = "TEXT")
    public String getLikedIds() {
        return likedIds;
    }

    public void setLikedIds(String likedIds) {
        this.likedIds = likedIds;
    }*/

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "complaint")
    public Integer getComplaint() {
        return complaint;
    }

    public void setComplaint(Integer complaint) {
        this.complaint = complaint;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "valid")
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "verified")
    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    @Basic
    @Column(name = "profile_photo_path")
    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public void setProfilePhotoPath(String profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "block_list")
    public String getBlockList() {
        return blockList;
    }

    public void setBlockList(String blockList) {
        this.blockList = blockList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberEntity that = (MemberEntity) o;

        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (blockList != null ? !blockList.equals(that.blockList) : that.blockList != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (profilePhotoPath != null ? !profilePhotoPath.equals(that.profilePhotoPath) : that.profilePhotoPath != null)
            return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (verified != null ? !verified.equals(that.verified) : that.verified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (verified != null ? verified.hashCode() : 0);
        result = 31 * result + (profilePhotoPath != null ? profilePhotoPath.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (blockList != null ? blockList.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "member")
    public Collection<EducationExperienceEntity> getEducationExperienceCollection() {
        return educationExperienceCollection;
    }

    public void setEducationExperienceCollection(Collection<EducationExperienceEntity> educationExperienceCollection) {
        this.educationExperienceCollection = educationExperienceCollection;
    }

    @OneToMany(mappedBy = "member")
    public Collection<JobPostEntity> getJobPostCollection() {
        return jobPostCollection;
    }

    public void setJobPostCollection(Collection<JobPostEntity> jobPostCollection) {
        this.jobPostCollection = jobPostCollection;
    }

    @OneToMany(mappedBy = "member")
    public Collection<MemberRoleEntity> getMemberRoleCollection() {
        return memberRoleCollection;
    }

    public void setMemberRoleCollection(Collection<MemberRoleEntity> memberRoleCollection) {
        this.memberRoleCollection = memberRoleCollection;
    }

    @OneToMany(mappedBy = "member")
    public List<ReviewEntity> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReviewEntity> replyList) {
        this.replyList = replyList;
    }

    @OneToMany(mappedBy = "member")
    public Collection<NewsEntity> getNewsCollection() {
        return newsCollection;
    }

    public void setNewsCollection(Collection<NewsEntity> newsCollection) {
        this.newsCollection = newsCollection;
    }

    @OneToMany(mappedBy = "member")
    public Collection<NotificationEntity> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<NotificationEntity> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @OneToMany(mappedBy = "member")
    public Collection<ResumeEntity> getResumeCollection() {
        return resumeCollection;
    }

    public void setResumeCollection(Collection<ResumeEntity> resumeCollection) {
        this.resumeCollection = resumeCollection;
    }

    @OneToMany(mappedBy = "member")
    public Collection<ReviewEntity> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<ReviewEntity> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @OneToMany(mappedBy = "member")
    public Collection<SecondHandPostEntity> getSecondHandPostCollection() {
        return secondHandPostCollection;
    }

    public void setSecondHandPostCollection(Collection<SecondHandPostEntity> secondHandPostCollection) {
        this.secondHandPostCollection = secondHandPostCollection;
    }
}
