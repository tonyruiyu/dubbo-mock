package com.tony.test.mock.po;

import java.util.List;

import com.google.common.collect.Lists;

public class MockOperDefine {

    private List<MockServiceDefine> mockServices       = Lists.newArrayList();

    private List<ProtocolConfig>    protocolConfigs    = Lists.newArrayList();

    private List<RegistryConfig>    registryConfigs    = Lists.newArrayList();

    private List<ServiceMethedRule> serviceMethedRules = Lists.newArrayList();

    private List<String>            mockRuleNames      = Lists.newArrayList();

    public List<String> getMockRuleNames() {
        return mockRuleNames;
    }

    public void setMockRuleNames(List<String> mockRuleNames) {
        this.mockRuleNames = mockRuleNames;
    }

    public List<MockServiceDefine> getMockServices() {
        return mockServices;
    }

    public void setMockServices(List<MockServiceDefine> mockServices) {
        this.mockServices = mockServices;
    }

    public List<ProtocolConfig> getProtocolConfigs() {
        return protocolConfigs;
    }

    public void setProtocolConfigs(List<ProtocolConfig> protocolConfigs) {
        this.protocolConfigs = protocolConfigs;
    }

    public List<RegistryConfig> getRegistryConfigs() {
        return registryConfigs;
    }

    public void setRegistryConfigs(List<RegistryConfig> registryConfigs) {
        this.registryConfigs = registryConfigs;
    }

    public List<ServiceMethedRule> getServiceMethedRules() {
        return serviceMethedRules;
    }

    public void setServiceMethedRules(List<ServiceMethedRule> serviceMethedRules) {
        this.serviceMethedRules = serviceMethedRules;
    }

}
