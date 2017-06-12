package com.tony.test.controller;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tony.test.mock.auto.mapper.MockTestParamMapper;
import com.tony.test.mock.auto.mapper.MockTestSubjectMapper;
import com.tony.test.mock.po.MockServiceDefine;
import com.tony.test.mock.po.MockTestParam;
import com.tony.test.mock.po.MockTestParamExample;
import com.tony.test.mock.po.MockTestSubject;
import com.tony.test.mock.po.MockTestSubjectExample;
import com.tony.test.page.Page;

@Controller public class MockTestSubjectParamController extends BaseController {

    @Resource MockTestParamMapper   mockTestParamMapper;

    @Resource MockTestSubjectMapper mockTestSubjectMapper;

    @ResponseBody
    @RequestMapping(value = "/selectMockTestInfos")
    public String selectMockTestInfos(HttpServletRequest arg0, Boolean selectFlag) throws Exception {
        Map<String, List<MockTestParam>> map = Maps.newConcurrentMap();
        Page<MockServiceDefine> page = new Page<>();
        Map<String, String> paraMap = assembleRequestParamForMap(arg0, page);
        MockTestSubjectExample example = new MockTestSubjectExample();
        MockTestSubjectExample.Criteria criteria = example.createCriteria();
        criteria.andServiceidEqualTo(Integer.valueOf(paraMap.get("serviceId")));
        List<MockTestSubject> subs = mockTestSubjectMapper.selectByExample(example);
        List<Integer> integers = Lists.newArrayList();
        for (MockTestSubject mockTestSubject : subs) {
            integers.add(mockTestSubject.getId());
        }
        MockTestParamExample example1 = new MockTestParamExample();
        MockTestParamExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andSubjectIdIn(integers);
        List<MockTestParam> params = mockTestParamMapper.selectByExampleWithBLOBs(example1);
        for (MockTestSubject mockTestSubject : subs) {
            String subName = mockTestSubject.getTestSubjectTitle();
            Integer id = mockTestSubject.getId();
            List<MockTestParam> params2 = Lists.newArrayList();
            for (MockTestParam mockTestParam : params) {
                if (mockTestParam.getSubjectId() == id) {
                    params2.add(mockTestParam);
                }
            }
            map.put(subName, params2);
        }
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(value = "/saveMockTestInfos")
    public String saveMockTestInfos(HttpServletRequest arg0) throws Exception {
        JSONObject object = new JSONObject();
        Map<String, String> context = Maps.newLinkedHashMap();
        List<MockTestParam> paramLists = Lists.newArrayList();
        object.put("result", "success");
        String result = null;
        String method = null;
        try {
            if (StringUtils.isNotBlank(arg0.getParameter("testSubjectTitle"))) {
                String mockRules = arg0.getParameter("mockRules");
                mockRules = URLDecoder.decode(mockRules, Charset.defaultCharset().toString());
                String[] params = mockRules.split("&");
                List<String> mockKeys = Lists.newArrayList();
                List<String> mockValus = Lists.newArrayList();
                List<String> mockTypes = Lists.newArrayList();
                for (int i = 0; i < params.length; i++) {
                    String[] param = params[i].split("=");
                    if (param[0].equals("mockTestKey")) {
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
                    if (i > 0) {
                        MockTestParam param = new MockTestParam();
                        param.setParKey(mockKeys.get(i));
                        param.setParType(mockTypes.get(i));
                        param.setParValue(mockValus.get(i));
                        paramLists.add(param);
                    }
                    //                    if (ArrayUtils.contains(intelChars, mockKeys.get(i))) {
                    //                        throw new RuntimeException("命名不能包含以下关键字:" + ArrayUtils.toString(intelChars));
                    //                    }
                    context.put(mockKeys.get(i), mockValus.get(i));
                }
                method = context.remove("methodName");
                MockTestSubject subject = new MockTestSubject();
                subject.setMethod(method);
                subject.setServiceid(Integer.valueOf(arg0.getParameter("serviceId")));
                subject.setTestSubjectTitle(arg0.getParameter("testSubjectTitle"));
                // 删除主表 和 子表
                MockTestSubjectExample example = new MockTestSubjectExample();
                MockTestSubjectExample.Criteria criteria = example.createCriteria();
                criteria.andTestSubjectTitleEqualTo(subject.getTestSubjectTitle());
                criteria.andServiceidEqualTo(subject.getServiceid());
                List<MockTestSubject> subjects = mockTestSubjectMapper.selectByExample(example);
                if (CollectionUtils.isNotEmpty(subjects)) {
                    mockTestSubjectMapper.deleteByPrimaryKey(subjects.get(0).getId());
                    MockTestParamExample example1 = new MockTestParamExample();
                    MockTestParamExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andSubjectIdEqualTo(subjects.get(0).getId());
                    mockTestParamMapper.deleteByExample(example1);
                }
                // 插入 主表 和 子表
                mockTestSubjectMapper.insert(subject);
                subjects = mockTestSubjectMapper.selectByExample(example);
                for (int i = 0; i < paramLists.size(); i++) {
                    MockTestParam p = paramLists.get(i);
                    p.setSubjectId(subjects.get(0).getId());
                    mockTestParamMapper.insert(p);
                }
            } else {
                object.put("result", "error");
                result = "主题不能为空！";
            }
        } catch (Exception e) {
            object.put("result", "error");
            result = e.getMessage();
            e.printStackTrace();
        }
        object.put("context", result);
        return object.toJSONString();
    }

}
