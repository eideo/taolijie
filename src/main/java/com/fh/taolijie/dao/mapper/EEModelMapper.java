package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.domain.EEModel;
import org.springframework.stereotype.Repository;

@Repository
public interface EEModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table education_experience
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table education_experience
     *
     * @mbggenerated
     */
    int insert(EEModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table education_experience
     *
     * @mbggenerated
     */
    int insertSelective(EEModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table education_experience
     *
     * @mbggenerated
     */
    EEModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table education_experience
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(EEModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table education_experience
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EEModel record);
}