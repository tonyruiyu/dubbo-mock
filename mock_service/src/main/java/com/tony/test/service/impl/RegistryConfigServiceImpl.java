package com.tony.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.tony.test.ReflectUtil;
import com.tony.test.mock.auto.mapper.RegistryConfigMapper;
import com.tony.test.mock.po.RegistryConfig;
import com.tony.test.mock.po.RegistryConfigExample;
import com.tony.test.service.RegistryConfigService;

@Service public class RegistryConfigServiceImpl implements RegistryConfigService {

    @Resource RegistryConfigMapper registryConfigMapper;

    @Override
    public List<RegistryConfig> selectRegistryConfig(RegistryConfig registryConfig) {
        RegistryConfigExample example = new RegistryConfigExample();
        ReflectUtil.invokeSelectParams(example, registryConfig);
        return registryConfigMapper.selectByExample(example);
    }

    @Override
    public int updateOrInsertRegistryConfig(RegistryConfig registryConfig) {
        if (registryConfig == null) {
            return 0;
        }
        registryConfig.setUpdateTime(new Date());
        int count = registryConfigMapper.updateByPrimaryKeySelective(registryConfig);
        if (count <= 0) {
            RegistryConfigExample example = new RegistryConfigExample();
            ReflectUtil.invokeSelectParams(example, registryConfig);
            count = registryConfigMapper.insert(registryConfig);
            List<RegistryConfig> services = selectRegistryConfig(registryConfig);
            int id = 0;
            if (CollectionUtils.isNotEmpty(services)) {
                for (RegistryConfig service : services) {
                    if (service.getId() > id) {
                        id = service.getId();
                    }
                }
            }
            registryConfig.setId(id);
        }
        return count;
    }

    @Override
    public int deleteRegistryConfig(RegistryConfig registryConfig) {
        RegistryConfigExample example = new RegistryConfigExample();
        ReflectUtil.invokeSelectParams(example, registryConfig);
        return registryConfigMapper.deleteByExample(example);
    }

}
