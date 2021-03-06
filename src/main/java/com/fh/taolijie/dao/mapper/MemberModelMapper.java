package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.dto.CreditsInfo;
import com.fh.taolijie.domain.MemberModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int insert(MemberModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int insertSelective(MemberModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    MemberModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MemberModel record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MemberModel record);

    MemberModel selectByUsername(String username);

    Long getMemberAmount();

    boolean checkUserExist(String username);

    MemberModel selectByIdentifier(String identifier);

    List<MemberModel> getMemberList(Map<String, Integer> pageMap);
    long countGetMemberList();

    CreditsInfo queryCreditsInfo(Integer memberId);

    void validMemberById(@Param("memberId") Integer memberId, @Param("valid") boolean valid);

    MemberModel findByEmail(String email);

    void addCredits(@Param("memId") Integer memId, @Param("value") int value, @Param("newLevel") String newLevel);
}