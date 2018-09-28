package com.example.demo.entity;

import java.util.Date;

/**
 * 
 * 流程记录表
 */
public class ActivitiRecord {
    /**
     * 
     */
    private Integer id;
    /**
     * 流程名
     */
    private String activitiName;
    /**
     * 发布时间
     */
    private Date deleaseDate;
    /**
     * 更新时间
     */
    private Date udpateTime;
    /**
     * 是否删除：0：否；1：是
     */
    private Integer isDelete;
    /**
     * 添加人
     */
    private String adder;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 修改人
     */
    private String moder;
    /**
     * 修改时间
     */
    private Date modTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivitiName() {
        return activitiName;
    }

    public void setActivitiName(String activitiName) {
        this.activitiName = activitiName == null ? null : activitiName.trim();
    }

    public Date getDeleaseDate() {
        return deleaseDate;
    }

    public void setDeleaseDate(Date deleaseDate) {
        this.deleaseDate = deleaseDate;
    }

    public Date getUdpateTime() {
        return udpateTime;
    }

    public void setUdpateTime(Date udpateTime) {
        this.udpateTime = udpateTime;
    }

    public String getAdder() {
        return adder;
    }

    public void setAdder(String adder) {
        this.adder = adder == null ? null : adder.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getModer() {
        return moder;
    }

    public void setModer(String moder) {
        this.moder = moder == null ? null : moder.trim();
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    /**
     * @return the isDelete
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}