package com.tony.test.mock.po;

import java.util.Date;

public class ServiceMethedRule {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer serviceId;

    /**
     * 
     */
    private String methodName;

    /**
     * 
     */
    private String whenScript;

    /**
     * 
     */
    private String returnMessage;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer execSort;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return service_id 
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * 
     * @param serviceId 
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 
     * @return method_name 
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 
     * @param methodName 
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * 
     * @return when_script 
     */
    public String getWhenScript() {
        return whenScript;
    }

    /**
     * 
     * @param whenScript 
     */
    public void setWhenScript(String whenScript) {
        this.whenScript = whenScript == null ? null : whenScript.trim();
    }

    /**
     * 
     * @return return_message 
     */
    public String getReturnMessage() {
        return returnMessage;
    }

    /**
     * 
     * @param returnMessage 
     */
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage == null ? null : returnMessage.trim();
    }

    /**
     * 
     * @return update_time 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return exec_sort 
     */
    public Integer getExecSort() {
        return execSort;
    }

    /**
     * 
     * @param execSort 
     */
    public void setExecSort(Integer execSort) {
        this.execSort = execSort;
    }
}