package com.fh.taolijie.dao.mapper.v2;

import com.fh.taolijie.domain.SchoolActModel;

public interface SchoolActModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity
     *
     * @mbggenerated
     */
    int insert(SchoolActModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity
     *
     * @mbggenerated
     */
    int insertSelective(SchoolActModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity
     *
     * @mbggenerated
     */
    SchoolActModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SchoolActModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(SchoolActModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table school_activity
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SchoolActModel record);
}