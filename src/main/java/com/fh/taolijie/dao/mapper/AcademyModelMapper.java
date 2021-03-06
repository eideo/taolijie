package com.fh.taolijie.dao.mapper;

import com.fh.taolijie.domain.AcademyModel;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table academy
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table academy
     *
     * @mbggenerated
     */
    int insert(AcademyModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table academy
     *
     * @mbggenerated
     */
    int insertSelective(AcademyModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table academy
     *
     * @mbggenerated
     */
    AcademyModel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table academy
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AcademyModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table academy
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AcademyModel record);
}