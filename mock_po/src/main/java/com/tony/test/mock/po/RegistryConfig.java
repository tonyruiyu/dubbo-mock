package com.tony.test.mock.po;

import java.util.Date;

public class RegistryConfig {
    private Integer id;

    private String registryProtocol;

    private String registryAddress;

    private Integer registryTimeout;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistryProtocol() {
        return registryProtocol;
    }

    public void setRegistryProtocol(String registryProtocol) {
        this.registryProtocol = registryProtocol == null ? null : registryProtocol.trim();
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress == null ? null : registryAddress.trim();
    }

    public Integer getRegistryTimeout() {
        return registryTimeout;
    }

    public void setRegistryTimeout(Integer registryTimeout) {
        this.registryTimeout = registryTimeout;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}