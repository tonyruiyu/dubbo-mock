package com.tony.test.service;

import java.util.List;

import com.tony.test.mock.po.ServiceMethedRule;

public interface ServiceMethedRuleService {
    List<ServiceMethedRule> selectServiceMethedRule(ServiceMethedRule service);

    int updateOrInsertServiceMethedRule(ServiceMethedRule ServiceMethedRule);

    int deleteServiceMethedRule(ServiceMethedRule ServiceMethedRule);
}
