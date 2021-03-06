package com.fh.taolijie.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PrivateNotificationModel extends Pageable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 通知发送者的id
     */
    @NotNull
    private Integer memberId;
    private MemberModel member;

    /**
     * 通知接收者的id
     */
    @NotNull
    private Integer toMemberId;

    /**
     * 通知标题
     */
    @NotNull
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.is_read
     *
     * @mbggenerated
     */
    private boolean isRead;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.time
     *
     * @mbggenerated
     */
    private Date time;

    /**
     *
     * @deprecated
     */
    private String accessRange;

    /**
     * 通知内容
     */
    private String content;

    public PrivateNotificationModel() {}
    public PrivateNotificationModel(int pageNumber, int pageSize) {
        super(pageNumber, pageSize);
    }

    public Integer getToMemberId() {
        return toMemberId;
    }

    public void setToMemberId(Integer toMemberId) {
        this.toMemberId = toMemberId;
    }

    public MemberModel getMember() {
        return member;
    }

    public void setMember(MemberModel member) {
        this.member = member;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.id
     *
     * @return the value of notification.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.id
     *
     * @param id the value for notification.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.member_id
     *
     * @return the value of notification.member_id
     *
     * @mbggenerated
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.member_id
     *
     * @param memberId the value for notification.member_id
     *
     * @mbggenerated
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.title
     *
     * @return the value of notification.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.title
     *
     * @param title the value for notification.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.time
     *
     * @return the value of notification.time
     *
     * @mbggenerated
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.time
     *
     * @param time the value for notification.time
     *
     * @mbggenerated
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.access_range
     *
     * @return the value of notification.access_range
     *
     * @mbggenerated
     */
    public String getAccessRange() {
        return accessRange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.access_range
     *
     * @param accessRange the value for notification.access_range
     *
     * @mbggenerated
     */
    public void setAccessRange(String accessRange) {
        this.accessRange = accessRange == null ? null : accessRange.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.content
     *
     * @return the value of notification.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.content
     *
     * @param content the value for notification.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}