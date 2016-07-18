package com.kennedy.springdemo.common;

import java.io.Serializable;

public class ResultMsg implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4917200971782481373L;
    private Boolean isSuccess;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
