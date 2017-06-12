package com.tony.test.protocol;

public interface MockServer {

    /**
     * 重新加载服务
     *
     * @param serviceId
     */
    String reloadService(int serviceId);

    /**
     * 停止服务
     *
     * @param serviceId
     */
    String stopService(int serviceId);

    /**
     * 是否已经发布
     * 
     * @param serviceId
     * @return
     */
    boolean isexport(int serviceId);

    /**
     * 启动服务
     * 
     * @param serviceId
     */
    String startService(int serviceId);
    /**
     * 
     * 构建dubbo通用mock服务
     * 
     * @param serviceId
     * @param ruleIds
     * @return
     */
    MockGenericService buildMockGenericService(int serviceId, Integer... ruleIds);

    void start();

    void stop();

}
