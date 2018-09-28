package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.ActivitiRecord;



public interface ActivitiRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivitiRecord record);

    int insertSelective(ActivitiRecord record);

    ActivitiRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivitiRecord record);

    int updateByPrimaryKey(ActivitiRecord record);
    /**
     * 
     * 根据流程文件名，查询流程文件
     *
     * @param activitiName:流程文件名
     * @return
     */
    ActivitiRecord queryByForActivitiName(@Param("activitiName")String activitiName);
}