package com.tony.test.service;

import com.tony.test.mock.po.MockOperDefine;
import com.tony.test.mock.po.MockServiceDefine;

public interface MockOperDefineService {

    MockOperDefine selectMockOperDefine(MockServiceDefine service);
}
