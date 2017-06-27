package com.tony.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.tony.test.ReflectUtil;
import com.tony.test.mock.auto.mapper.MockServiceMapper;
import com.tony.test.mock.define.mapper.MockServiceDefineMapper;
import com.tony.test.mock.po.MockService;
import com.tony.test.mock.po.MockServiceDefine;
import com.tony.test.mock.po.MockServiceExample;
import com.tony.test.protocol.MockServer;
import com.tony.test.service.MockServiceService;

@Service public class MockServiceServiceImpl implements MockServiceService {

    @Resource MockServiceMapper                    mockServiceMapper;

    @Resource MockServiceDefineMapper              mockServiceDefineMapper;

    @Resource(name = "dubboMockServer") MockServer mockServer;

    @Override
    public List<MockService> selectMockService(MockService mockService) {
        MockServiceExample example = new MockServiceExample();
        ReflectUtil.invokeSelectParams(example, mockService);
        return mockServiceMapper.selectByExample(example);
    }

    @Override
    public int updateOrInsertMockService(MockService mockService) {
        if (mockService == null) {
            return 0;
        }
        int count = 0;
        try {
            count = mockServiceMapper.updateByPrimaryKeySelective(mockService);
            mockService.setUpdateTime(new Date());
            if (count > 0) {
                if ("running".equals(mockService.getServiceStatus())) {
                    mockServer.startService(mockService.getId());
                } else if ("stop".equals(mockService.getServiceStatus())) {
                    mockServer.stopService(mockService.getId());
                }
            } else {
                count = mockServiceMapper.insert(mockService);
                List<MockService> services = selectMockService(mockService);
                int id = 0;
                if (CollectionUtils.isNotEmpty(services)) {
                    for (MockService service : services) {
                        if (service.getId() > id) {
                            id = service.getId();
                        }
                    }
                }
                mockService.setId(id);
            }
        } catch (Exception e) {
            return count;
        }
        return count;
    }

    @Override
    public int deleteMockService(MockService mockService) {
        return mockServiceMapper.deleteByPrimaryKey(mockService.getId());
    }

    @Override
    public List<MockServiceDefine> selectMockServiceDefine(MockServiceDefine serviceDefine) {
        return mockServiceDefineMapper.selectMockServiceDefine(serviceDefine);
    }

    @Override
    public List<String> selectMockRoleNames(Integer id) {
        return mockServiceDefineMapper.selectMockRoleNames(id);
    }

}
