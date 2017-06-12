package com.tony.mock;

import java.util.Map;

/**
 * 消息模板接口
 * 
 * @author zhangruiyu zhangry@metersbonwe.com
 * @date 2016年8月26日 上午1:45:27
 * @since V1.0
 * @version V1.0
 */
public interface Template {

    /**
     * 获得模板ID
     * 
     * @return
     */
    public String getId();

    /**
     * 模板渲染
     * 
     * @param context
     *            需要渲染的数据
     * @return
     * @throws Exception
     */
    public String resolve(Map<String, ?> context);

    /**
     * 获得模板原生数据，未渲染前
     * 
     * @return
     */
    public String getRaw();

    /**
     * 是否动态的
     * 
     * @return
     */
    public boolean isDynamic();
}
