package com.tony.test.protocol;

import org.apache.commons.lang.StringUtils;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.google.common.collect.Lists;
import com.tony.test.mock.po.MockService;

public class DubboServiceConfig {

    public ServiceConfig<GenericService> fillDubboService(MockService mockService, com.tony.test.mock.po.RegistryConfig registryConfig,
            com.tony.test.mock.po.ProtocolConfig protocolConfig, MockGenericService tmpMockservice) {
        ServiceConfig<GenericService> service = new ServiceConfig<GenericService>();
        service.setInterface(mockService.getServiceInterface());
        service.setRef(tmpMockservice); // 指向一个通用服务实现 
        RegistryConfig registry = createRegistry(registryConfig.getRegistryAddress(), registryConfig.getRegistryTimeout());
        service.setRegistry(registry);
        service.setProtocols(Lists.newArrayList(new ProtocolConfig(protocolConfig.getProtocolName(), protocolConfig.getProtocolPort())));
        if (!StringUtils.isBlank(mockService.getGroupName())) {
            service.setGroup(mockService.getGroupName());
        }
        service.setTimeout(mockService.getTimeout());
        service.setRetries(mockService.getRetries());
        service.setApplication(new ApplicationConfig(mockService.getApplicationName()));
        return service;
    }

    public static RegistryConfig createRegistry(String address, int timeout) {
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress(address);
        registry.setTimeout(timeout);
        return registry;
    }
}
