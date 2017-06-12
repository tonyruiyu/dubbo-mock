package com.tony.test.protocol;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.google.common.collect.Maps;
import com.tony.test.mock.auto.mapper.MockServiceMapper;
import com.tony.test.mock.auto.mapper.ProtocolConfigMapper;
import com.tony.test.mock.auto.mapper.RegistryConfigMapper;
import com.tony.test.mock.auto.mapper.ServiceMethedRuleMapper;
import com.tony.test.mock.po.MockService;
import com.tony.test.mock.po.MockServiceExample;
import com.tony.test.mock.po.ProtocolConfig;
import com.tony.test.mock.po.RegistryConfig;
import com.tony.test.mock.po.ServiceMethedRule;
import com.tony.test.mock.po.ServiceMethedRuleExample;

@Service public class DubboMockServer implements MockServer {

    public static final String                         RUNNING            = "running";

    public static final String                         STOP               = "stop";

    private Map<Integer, MockGenericService>            mockServices       = Maps.newHashMap();

    private Map<Integer, ServiceConfig<GenericService>> dubboServices      = Maps.newHashMap();

    private DubboServiceConfig                          dubboServiceConfig = new DubboServiceConfig();

    @Resource ProtocolConfigMapper                      protocolConfigMapper;

    @Resource RegistryConfigMapper                      registryConfigMapper;

    @Resource ServiceMethedRuleMapper                   serviceMethedRuleMapper;

    @Resource MockServiceMapper                         mockServiceMapper;

    /**
     * @see com.tony.test.protocol.MockServer#reloadService(int)
     */
    @Override
    public synchronized String reloadService(int serviceId) {
        return startService(serviceId);
    }

    /**
     * @see com.tony.test.protocol.MockServer#stopService(int)
     */
    @Override
    public synchronized String stopService(int serviceId) {
        unexportService(serviceId);
        String result = getServiceStatus(serviceId);
        if (StringUtils.equals(result, STOP)) {
            clearLocalCache(serviceId);
        }
        return result;
    }

    private String getServiceStatus(int serviceId) {
        return this.isexport(serviceId) ? RUNNING : STOP;
    }

    /**
     * @see com.tony.test.protocol.MockServer#isexport(int)
     */
    @Override
    public synchronized boolean isexport(int serviceId) {
        if (!dubboServices.containsKey(serviceId)) {
            return false;
        }
        return dubboServices.get(serviceId).isExported();
    }

    /**
     * @see com.tony.test.protocol.MockServer#startService(int)
     */
    @Override
    public synchronized String startService(int serviceId) {
        //重新加载服务配置
        MockService mockService = selectMockService(serviceId);
        List<ServiceMethedRule> methodRules = selectMethodRule(serviceId);
        RegistryConfig registryConfig = selectRegistryConfig(mockService.getRegistryId());
        ProtocolConfig protocolConfig = selectProtocolConfig(mockService.getProtocolId());

        Assert.notNull(mockService, "服务id:" + serviceId + "不存在");
        Assert.notNull(methodRules, "ServiceMethedRule:" + serviceId + "不存在");
        Assert.notNull(methodRules, "registryConfig:" + serviceId + "不存在");
        Assert.notNull(methodRules, "protocolConfig:" + serviceId + "不存在");

        unexportService(serviceId); //卸载服务
        clearLocalCache(serviceId); //清除本地缓存

        //构建服务通用mock对象
        MockGenericService tmpMockservice = new MockGenericService(mockService, methodRules);
        ServiceConfig<GenericService> service = dubboServiceConfig.fillDubboService(mockService, registryConfig, protocolConfig, tmpMockservice);

        service.export(); // 暴露及注册服务 

        updateLocalCache(serviceId, tmpMockservice, service);//更新本地缓存

        return getServiceStatus(serviceId);
    }

    @Override
    public MockGenericService buildMockGenericService(int serviceId, Integer... ruleIds) {
        MockService mockService = selectMockService(serviceId);
        List<ServiceMethedRule> methodRules = null;
        if (ruleIds == null || ruleIds.length < 1) {
            methodRules = selectMethodRule(serviceId);
        } else {
            methodRules = selectMethodRule(serviceId, ruleIds);
        }
        return new MockGenericService(mockService, methodRules);
    }

    private List<ServiceMethedRule> selectMethodRule(int serviceId, Integer[] ruleIds) {
        ServiceMethedRuleExample example = new ServiceMethedRuleExample();
        example.or().andServiceIdEqualTo(serviceId).andIdIn(Arrays.asList(ruleIds));
        return serviceMethedRuleMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 更新本地缓存
     *
     * @param serviceId
     * @param tmpMockservice
     * @param service
     */
    private void updateLocalCache(int serviceId, MockGenericService tmpMockservice, ServiceConfig<GenericService> service) {
        mockServices.put(serviceId, tmpMockservice);
        dubboServices.put(serviceId, service);
    }

    //卸载服务
    private void unexportService(int serviceId) {
        if (dubboServices.containsKey(serviceId)) {
            ServiceConfig<GenericService> tempService = dubboServices.get(serviceId);
            if (tempService.isExported()) {
                tempService.unexport();
            }
        }
    }

    //清除本地缓存
    private void clearLocalCache(int serviceId) {
        mockServices.remove(serviceId);
        dubboServices.remove(serviceId);
    }

    private ProtocolConfig selectProtocolConfig(int protocolId) {
        return protocolConfigMapper.selectByPrimaryKey(protocolId);
    }

    private RegistryConfig selectRegistryConfig(int registryId) {
        return registryConfigMapper.selectByPrimaryKey(registryId);
    }

    private List<ServiceMethedRule> selectMethodRule(int serviceId) {
        ServiceMethedRuleExample example = new ServiceMethedRuleExample();
        example.or().andServiceIdEqualTo(serviceId);
        return serviceMethedRuleMapper.selectByExampleWithBLOBs(example);
    }

    private MockService selectMockService(int serviceId) {
        return mockServiceMapper.selectByPrimaryKey(serviceId);
    }

    @Override
    public synchronized void start() {
        try {
            List<MockService> items = selectStartedService();
            loopStartService(items);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private List<MockService> selectStartedService() {
        MockServiceExample example = new MockServiceExample();
        example.or().andServiceStatusEqualTo(RUNNING);
        List<MockService> items = mockServiceMapper.selectByExample(example);
        Assert.notEmpty(items, "没有需要启动的服务");
        return items;
    }

    private void loopStartService(List<MockService> items) {
        for (int i = 0; i < items.size(); i++) {
            try {
                MockService ms = items.get(i);
                startService(ms.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void stop() {
        Iterator<Integer> iterator = dubboServices.keySet().iterator();
        for (; iterator.hasNext();) {
            Integer key = iterator.next();
            stopService(key);
        }
    }

}
