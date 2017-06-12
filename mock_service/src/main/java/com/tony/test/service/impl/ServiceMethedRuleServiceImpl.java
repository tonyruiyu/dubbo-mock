package com.tony.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.tony.test.ReflectUtil;
import com.tony.test.mock.auto.mapper.ServiceMethedRuleMapper;
import com.tony.test.mock.po.ServiceMethedRule;
import com.tony.test.mock.po.ServiceMethedRuleExample;
import com.tony.test.service.ServiceMethedRuleService;

@Service public class ServiceMethedRuleServiceImpl implements ServiceMethedRuleService {

    @Resource ServiceMethedRuleMapper serviceMethedRuleMapper;

    @Override
    public List<ServiceMethedRule> selectServiceMethedRule(ServiceMethedRule serviceMethedRule) {
        ServiceMethedRuleExample example = new ServiceMethedRuleExample();
        ReflectUtil.invokeSelectParams(example, serviceMethedRule);
        example.setOrderByClause(" method_name,exec_sort");
        return serviceMethedRuleMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int updateOrInsertServiceMethedRule(ServiceMethedRule serviceMethedRule) {
        if (serviceMethedRule == null) {
            return 0;
        }
        serviceMethedRule.setUpdateTime(new Date());
        int count = serviceMethedRuleMapper.updateByPrimaryKeyWithBLOBs(serviceMethedRule);
        if (count <= 0) {
            ServiceMethedRuleExample example = new ServiceMethedRuleExample();
            ReflectUtil.invokeSelectParams(example, serviceMethedRule);
            count = serviceMethedRuleMapper.insert(serviceMethedRule);
            List<ServiceMethedRule> services = selectServiceMethedRule(serviceMethedRule);
            int id = 0;
            if (CollectionUtils.isNotEmpty(services)) {
                for (ServiceMethedRule service : services) {
                    if (service.getId() > id) {
                        id = service.getId();
                    }
                }
            }
            serviceMethedRule.setId(id);
        }
        return count;
    }

    @Override
    public int deleteServiceMethedRule(ServiceMethedRule serviceMethedRule) {
        ServiceMethedRuleExample example = new ServiceMethedRuleExample();
        ReflectUtil.invokeSelectParams(example, serviceMethedRule);
        return serviceMethedRuleMapper.deleteByExample(example);
    }

}
