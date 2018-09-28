package com.example.demo.service;

import java.util.Date;

import com.example.demo.entity.ActivitiRecord;



/**
 * activiti 流程文件记录
 *
 * @author zhixiang.meng
 */
public interface ActivitiRecordService {
    /**
     * 
     * 根据流程文件名，查询流程文件
     *
     * @param activitiName:流程文件名
     * @return
     */
    ActivitiRecord queryByForActivitiName(String activitiName);
    /**
     * 保存流程文件
     * 流程文件存在不添加，不存在添加
     *
     * @param record
     * @param isQuery:true:表示查询；false：表示不查询
     * @return
     */
    boolean saveActivitiRecordService(ActivitiRecord record, boolean isQuery);
    /**
     * 
     * 更新流程文件的更新时间
     *
     * @param acitivitiId：流程文件id
     * @return
     */
    boolean updateActivitiRecordService(Integer acitivitiId, Date lastModifiedDate);

}
