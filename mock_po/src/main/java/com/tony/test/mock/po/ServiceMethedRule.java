package com.tony.test.mock.po;

import java.util.Date;

public class ServiceMethedRule {
    private Integer id;

    private Integer serviceId;

    private String methodName;

    private Date updateTime;

    private Integer execSort;

    private String whenScript;

    private String returnMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getExecSort() {
        return execSort;
    }

    public void setExecSort(Integer execSort) {
        this.execSort = execSort;
    }

    public String getWhenScript() {
        return whenScript;
    }

    public void setWhenScript(String whenScript) {
        this.whenScript = whenScript == null ? null : whenScript.trim();
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage == null ? null : returnMessage.trim();
    }
}