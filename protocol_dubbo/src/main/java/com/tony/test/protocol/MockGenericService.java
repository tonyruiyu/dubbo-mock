package com.tony.test.protocol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tony.test.mock.po.MockService;
import com.tony.test.mock.po.ServiceMethedRule;

public class MockGenericService implements GenericService {

    private Map<String, MethodRule> rules       = Maps.newHashMap();

    private MockService             mockService;

    private List<ServiceMethedRule> methodRules = Lists.newArrayList();

    public MockGenericService(MockService mockService, List<ServiceMethedRule> methodRules) {
        super();
        this.mockService = mockService;
        this.methodRules = methodRules;
        init();
    }

    public void init() {
        rules.clear();
        Map<String, List<ServiceMethedRule>> tempMap = Maps.newHashMap();
        for (int i = 0; i < methodRules.size(); i++) {
            ServiceMethedRule mrule = methodRules.get(i);
            if (!tempMap.containsKey(mrule.getMethodName())) {
                tempMap.put(mrule.getMethodName(), new ArrayList<ServiceMethedRule>());
            }
            tempMap.get(mrule.getMethodName()).add(mrule);
        }
        for (Iterator<String> iterator = tempMap.keySet().iterator(); iterator.hasNext();) {
            String methodName = iterator.next();
            MethodRule mr = new MethodRule(mockService, methodName, tempMap.get(methodName));
            rules.put(methodName, mr);
        }
    }

    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        MethodRule rule = rules.get(method);
        if (rule == null)
            return null;
        DataCountPointer countPointer = rule.getDataCountPointer();
        Map<String, Object> context = buildContext(method, parameterTypes, args, countPointer);
        String s = rule.resolve(context);
        return JSON.parse(s);
    }

    public Map<String, Object> buildContext(String method, String[] parameterTypes, Object[] args, DataCountPointer countPointer) {
        Map<String, Object> context = Maps.newHashMap();
        context.put("methodName", method);
        context.put("parameterTypes", parameterTypes);
        context.put("args", args);
        context.put("any", true);
        context.put("void", true);
        context.put("_idx", countPointer.getIdx());
        context.put("_random", countPointer.getRandomInt());

        if (args != null && args.length > 0) {
            context.put("arg", args[0]);
        }
        return context;
    }

    public MethodRule getMethodRule(String methodName) {
        return rules.get(methodName);
    }

    public Map<String, MethodRule> getRules() {
        return rules;
    }

    public MockService getMockService() {
        return mockService;
    }

    public List<ServiceMethedRule> getMethodRules() {
        return methodRules;
    }

}
