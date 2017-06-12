package com.tony.test.controller;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tony.test.mock.po.MockOperDefine;
import com.tony.test.mock.po.MockServiceDefine;
import com.tony.test.protocol.DubboMockServer;
import com.tony.test.protocol.MethodRule;
import com.tony.test.protocol.MockGenericService;
import com.tony.test.protocol.MockTestServiceImpl;
import com.tony.test.service.MockOperDefineService;
import com.tony.test.service.MockServiceService;

@Controller public class MockOperDefineController extends BaseController {

    @Resource MockOperDefineService mockOperDefineServiceImpl;

    @Resource MockServiceService    mockServiceServiceImpl;

    @Resource DubboMockServer       dubboMockServer;

    @Resource MockTestServiceImpl   mockTestServiceImpl;

    private String[]                intelChars = new String[] { "methodName", "any", "void", "_idx", "_random" };

    @ResponseBody
    @RequestMapping(value = "/selectMockOperDefine")
    public ModelAndView selectMockService(HttpServletRequest arg0, Boolean selectFlag) throws Exception {
        Map<String, String> paraMap = assembleRequestParamForMap(arg0, null);
        MockServiceDefine mockService = assembleRequestParamForBean(paraMap, MockServiceDefine.class);
        MockOperDefine mockOperDefine = mockOperDefineServiceImpl.selectMockOperDefine(mockService);
        mockOperDefine.setMockRuleNames(mockServiceServiceImpl.selectMockRoleNames(mockService.getId()));
        ModelAndView view = new ModelAndView("jsp/selectMockOperDefine");
        view.addObject("mockOperDefine", mockOperDefine);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/selectMockRuleScript")
    public String selectMockRuleScript(HttpServletRequest arg0, Boolean selectFlag) throws Exception {
        String serviceId = arg0.getParameter("serviceId");
        String mockTestIds = arg0.getParameter("mockTestIds");
        String[] mocks = mockTestIds.split(",");
        Integer[] integers = new Integer[mocks.length];
        for (int i = 0; i < mocks.length; i++) {
            integers[i] = Integer.valueOf(mocks[i]);
        }
        MockGenericService service = dubboMockServer.buildMockGenericService(Integer.valueOf(serviceId), integers);
        Map<String, String> map = Maps.newConcurrentMap();
        Set<String> set = service.getRules().keySet();
        for (String key : set) {
            MethodRule rule = service.getRules().get(key);
            map.put(key, rule.getTemplate().getRaw());
        }
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/selectMockRuleResults")
    public String selectMockRuleResults(HttpServletRequest arg0, Boolean selectFlag) throws Exception {
        JSONObject object = new JSONObject();
        object.put("result", "success");
        String method = null;
        String result = null;
        try {
            Map<String, String> context = Maps.newLinkedHashMap();
            String serviceId = arg0.getParameter("serviceId");
            String mockTestIds = arg0.getParameter("mockTestIds");
            String[] mocks = mockTestIds.split(",");
            Integer[] integers = new Integer[mocks.length];
            for (int i = 0; i < mocks.length; i++) {
                integers[i] = Integer.valueOf(mocks[i]);
            }
            String mockRules = arg0.getParameter("mockRules");
            mockRules = URLDecoder.decode(mockRules, Charset.defaultCharset().toString());
            String[] params = mockRules.split("&");
            List<String> mockKeys = Lists.newArrayList();
            List<String> mockValus = Lists.newArrayList();
            List<String> mockTypes = Lists.newArrayList();
            for (int i = 0; i < params.length; i++) {
                String[] param = params[i].split("=");
                if (param[0].equals("mockTestKey")) {
                    if (i > 0 && ArrayUtils.contains(intelChars, param[1])) {
                        throw new RuntimeException("KEY命名不能包含以下关键字:" + ArrayUtils.toString(intelChars));
                    }
                    mockKeys.add(param[1]);
                }
                if (param[0].equals("mockTestValue")) {
                    mockValus.add(param[1]);
                }
                if (param[0].equals("mockTestType")) {
                    mockTypes.add(param[1]);
                }
            }
            for (int i = 0; i < mockKeys.size(); i++) {
                context.put(mockKeys.get(i), mockValus.get(i));
            }
            method = context.remove("methodName");
            result = mockTestServiceImpl.testMockService(Integer.valueOf(serviceId), method, context, integers);
        } catch (Exception e) {
            object.put("result", "error");
            result = e.getMessage();
            e.printStackTrace();
        }
        object.put("context", result);
        return object.toJSONString();
    }
}
