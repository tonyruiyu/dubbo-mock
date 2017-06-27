package com.tony.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tony.test.mock.po.MockOperDefine;
import com.tony.test.mock.po.MockServiceDefine;
import com.tony.test.mock.po.ProtocolConfig;
import com.tony.test.mock.po.RegistryConfig;
import com.tony.test.mock.po.ServiceMethedRule;
import com.tony.test.service.MockOperDefineService;
import com.tony.test.service.MockServiceService;
import com.tony.test.service.ProtocolConfigService;
import com.tony.test.service.RegistryConfigService;
import com.tony.test.service.ServiceMethedRuleService;

@Service public class MockOperDefineServiceImpl implements MockOperDefineService {

    @Resource MockServiceService       mockServiceServiceImpl;

    @Resource ProtocolConfigService    protocolConfigServiceImpl;

    @Resource RegistryConfigService    registryConfigServiceImpl;

    @Resource ServiceMethedRuleService serviceMethedRuleServiceImpl;

    @Override
    public MockOperDefine selectMockOperDefine(MockServiceDefine service) {
        MockOperDefine define = new MockOperDefine();
        if (service.getId() == null) {
            service.setId(-1);
        }
        List<MockServiceDefine> mockServices = mockServiceServiceImpl.selectMockServiceDefine(service);
        List<ProtocolConfig> protocolConfigs = protocolConfigServiceImpl.selectProtocolConfig(null);
        List<RegistryConfig> registryConfigs = registryConfigServiceImpl.selectRegistryConfig(null);
        ServiceMethedRule rule = new ServiceMethedRule();
        rule.setServiceId(service.getId());
        List<ServiceMethedRule> serviceMethedRules = null;
		try {
			serviceMethedRules = serviceMethedRuleServiceImpl.selectServiceMethedRule(rule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (CollectionUtils.isEmpty(mockServices)) {
            mockServices = Lists.newArrayList();
            mockServices.add(new MockServiceDefine());
        }
        define.setMockServices(mockServices);
        define.setProtocolConfigs(protocolConfigs);
        define.setRegistryConfigs(registryConfigs);
        if (CollectionUtils.isNotEmpty(serviceMethedRules)) {
            define.setServiceMethedRules(serviceMethedRules);
        }
        return define;
    }

}
