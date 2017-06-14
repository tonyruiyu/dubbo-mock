package com.tony.test.protocol;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tony.mock.Template;

/**
 * 脚本测试
 * 
 * @since V1.0
 * @version V1.0
 */
@Service public class MockTestServiceImpl {

    @Resource MockServer dubboMockServer;

    /**
     * 测试规则
     *
     * @param serviceId
     * @param method
     * @param context
     * @param ids
     * @return
     */
    public String testMockService(int serviceId, String method, Map<String, String> context, Integer[] ids) {
        MockGenericService mgService = dubboMockServer.buildMockGenericService(serviceId, ids);
        Map<String, MethodRule> ruls = mgService.getRules();
        MethodRule merule = ruls.get(method);
        Template template = merule.getTemplate();
        Map<String, Object> map = buildContext(mgService, method, context);
        return template.resolve(map);
    }

    /**
     * 构建上下文对象
     * 
     * @param mgService
     * @param method
     * @param context
     *            上下文
     * @return
     */
    private Map<String, Object> buildContext(MockGenericService mgService, String method, Map<String, String> context) {
        Map<String, Object> temp = mgService.buildContext(method, null, null, mgService.getMethodRule(method).getDataCountPointer());
        for (Iterator<String> iterator = context.keySet().iterator(); iterator.hasNext();) {
            try {
                String key = iterator.next();
                String v = context.get(key);
                Object o = JSON.parse(v);
                temp.put(key, o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setArg(temp);
        return temp;
    }

    private void setArg(Map<String, Object> temp) {
        if (temp.containsKey("args")) {
            if (temp.get("args") instanceof List) {
                List<?> tList = (List<?>) temp.get("args");
                if (tList == null || tList.isEmpty())
                    return;
                if (temp.get("arg") == null) {
                    temp.put("arg", tList.get(0));
                }
            }
        }

    }

}
