package com.mxk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 
 */
@Data
public class ShopCategory implements Serializable {
    /**
     * 店铺类别id
     */
    private Integer shopCategoryId;

    /**
     * 店铺类别名称
     */
    private String shopCategoryName;

    /**
     * 店铺类别描述
     */
    private String shopCategoryDesc;

    /**
     * 店铺类别图片地址
     */
    private String shopCategoryImg;

    /**
     * 店铺类别展示优先级
     */
    private Integer priority;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近一次修改时间
     */
    private Date lastEditTime;

    /**
     * 店铺类别的父类别
     */
    private Integer parentId;

    private static final long serialVersionUID = 1L;

    public Integer getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Integer shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        ShopCategory other = (ShopCategory) that;
        return (this.getShopCategoryId() == null ? other.getShopCategoryId() == null : this.getShopCategoryId().equals(other.getShopCategoryId()))
            && (this.getShopCategoryName() == null ? other.getShopCategoryName() == null : this.getShopCategoryName().equals(other.getShopCategoryName()))
            && (this.getShopCategoryDesc() == null ? other.getShopCategoryDesc() == null : this.getShopCategoryDesc().equals(other.getShopCategoryDesc()))
            && (this.getShopCategoryImg() == null ? other.getShopCategoryImg() == null : this.getShopCategoryImg().equals(other.getShopCategoryImg()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastEditTime() == null ? other.getLastEditTime() == null : this.getLastEditTime().equals(other.getLastEditTime()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShopCategoryId() == null) ? 0 : getShopCategoryId().hashCode());
        result = prime * result + ((getShopCategoryName() == null) ? 0 : getShopCategoryName().hashCode());
        result = prime * result + ((getShopCategoryDesc() == null) ? 0 : getShopCategoryDesc().hashCode());
        result = prime * result + ((getShopCategoryImg() == null) ? 0 : getShopCategoryImg().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastEditTime() == null) ? 0 : getLastEditTime().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopCategoryId=").append(shopCategoryId);
        sb.append(", shopCategoryName=").append(shopCategoryName);
        sb.append(", shopCategoryDesc=").append(shopCategoryDesc);
        sb.append(", shopCategoryImg=").append(shopCategoryImg);
        sb.append(", priority=").append(priority);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastEditTime=").append(lastEditTime);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}