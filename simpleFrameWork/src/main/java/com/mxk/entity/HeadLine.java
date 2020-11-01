package com.mxk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
@Data
public class HeadLine implements Serializable {
    /**
     * 头条id
     */
    private Integer lineId;

    /**
     * 头条名称
     */
    private String lineName;

    /**
     * 头条链接
     */
    private String lineLink;

    /**
     * 头条图片地址
     */
    private String lineImg;

    /**
     * 展示的优先级
     */
    private Integer priority;

    /**
     * 可用状态
     */
    private Integer enableStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近修改时间
     */
    private Date lastEditTime;

    private static final long serialVersionUID = 1L;

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLink() {
        return lineLink;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public String getLineImg() {
        return lineImg;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HeadLine other = (HeadLine) that;
        return (this.getLineId() == null ? other.getLineId() == null : this.getLineId().equals(other.getLineId()))
            && (this.getLineName() == null ? other.getLineName() == null : this.getLineName().equals(other.getLineName()))
            && (this.getLineLink() == null ? other.getLineLink() == null : this.getLineLink().equals(other.getLineLink()))
            && (this.getLineImg() == null ? other.getLineImg() == null : this.getLineImg().equals(other.getLineImg()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getEnableStatus() == null ? other.getEnableStatus() == null : this.getEnableStatus().equals(other.getEnableStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastEditTime() == null ? other.getLastEditTime() == null : this.getLastEditTime().equals(other.getLastEditTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLineId() == null) ? 0 : getLineId().hashCode());
        result = prime * result + ((getLineName() == null) ? 0 : getLineName().hashCode());
        result = prime * result + ((getLineLink() == null) ? 0 : getLineLink().hashCode());
        result = prime * result + ((getLineImg() == null) ? 0 : getLineImg().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getEnableStatus() == null) ? 0 : getEnableStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastEditTime() == null) ? 0 : getLastEditTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lineId=").append(lineId);
        sb.append(", lineName=").append(lineName);
        sb.append(", lineLink=").append(lineLink);
        sb.append(", lineImg=").append(lineImg);
        sb.append(", priority=").append(priority);
        sb.append(", enableStatus=").append(enableStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastEditTime=").append(lastEditTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}