package com.tony.test.mock.protocol;

import java.util.Map;

public interface Protocol {

    /**
     * 协议名称
     * 
     * @return 协议名称
     */
    int getPort();

    /**
     * 发布服务
     *
     * @param context
     */
    void export(Map<String, Object> context);

    /**
     * 注销发布
     *
     * @param context
     */
    void unexport(Map<String, Object> context);
}
