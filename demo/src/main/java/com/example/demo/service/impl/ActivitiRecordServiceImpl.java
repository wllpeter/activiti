package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ActivitiRecord;
import com.example.demo.mapper.ActivitiRecordMapper;
import com.example.demo.service.ActivitiRecordService;
import com.example.demo.util.DateUtils;




/**
 * 流程文件实现
 *
 * @author zhixiang.meng
 */
@Service("activitiRecordService")
public class ActivitiRecordServiceImpl implements ActivitiRecordService {
    
    @Autowired
    private ActivitiRecordMapper activitiRecordMapper;

    /* (non-Javadoc)
     * @see cn.sigo.recruit.service.ActivitiRecordService#queryByForActivitiName(java.lang.String)
     */
    @Override
    public ActivitiRecord queryByForActivitiName(String activitiName) {
        return activitiRecordMapper.queryByForActivitiName(activitiName);
    }

    /* (non-Javadoc)
     * @see cn.sigo.recruit.service.ActivitiRecordService#saveActivitiRecordService(cn.sigo.recruit.model.ActivitiRecord)
     */
    @Override
    public boolean saveActivitiRecordService(ActivitiRecord record, boolean isQuery) {
        ActivitiRecord queryRecord = null;
        if(isQuery) {
            queryRecord = this.queryByForActivitiName(record.getActivitiName());
        }
        if(null == queryRecord) {
            // 设置发布时间
            Date date = DateUtils.nowByDate(DateUtils.YYYY_MM_DD_HH_MM_SS);
            record.setAddTime(date);
            record.setAdder("admin");
            record.setIsDelete(0);
            int result = activitiRecordMapper.insert(record);
            return result > 0;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see cn.sigo.recruit.service.ActivitiRecordService#updateActivitiRecordService(java.lang.Integer)
     */
    @Override
    public boolean updateActivitiRecordService(Integer acitivitiId, Date lastModifiedDate) {
        ActivitiRecord record = new ActivitiRecord();
        record.setUdpateTime(lastModifiedDate);
        record.setModer("admin");
        record.setId(acitivitiId);
        return activitiRecordMapper.updateByPrimaryKeySelective(record) > 0;
    }

}
