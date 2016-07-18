package com.kennedy.springdemo.common;

import java.io.Serializable;

public class BaseData implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;
    private Boolean isDelete; // 是否删除；0：否，1：是

    public Integer getId() {
        return id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

}
