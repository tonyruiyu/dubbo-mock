package com.tony.test.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tony.test.mock.po.MockService;
import com.tony.test.mock.po.MockServiceDefine;
import com.tony.test.page.Page;
import com.tony.test.page.PageThreadLocal;
import com.tony.test.service.MockServiceService;

@Controller public class MockServiceController extends BaseController {

    @Resource MockServiceService mockServiceServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/selectMockService")
    public ModelAndView selectMockService(HttpServletRequest arg0, Boolean selectFlag) throws Exception {
        Page<MockServiceDefine> page = new Page<>();
        Map<String, String> paraMap = assembleRequestParamForMap(arg0, page);
        MockServiceDefine mockService = assembleRequestParamForBean(paraMap, MockServiceDefine.class);
        List<MockServiceDefine> results = mockServiceServiceImpl.selectMockServiceDefine(mockService);

        if (page.getPageNo() > page.getTotalPage()) {
            page.setPageNo(page.getTotalPage());
            PageThreadLocal.setThreadLocalPage(page);
            results = mockServiceServiceImpl.selectMockServiceDefine(mockService);
        }

        page.setResults(results);
        page.setParams(mockService);
        ModelAndView view = new ModelAndView("jsp/selectMockService");
        view.addObject("page", page);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/updateOrInsertMockService")
    public MockService updateOrInsertMockService(HttpServletRequest arg0) throws Exception {
        MockService mockService = assembleRequestParamForBean(arg0, MockService.class);
        mockServiceServiceImpl.updateOrInsertMockService(mockService);
        return mockService;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMockService")
    public ModelAndView deleteMockService(HttpServletRequest arg0) throws Exception {
        MockService mockService = assembleRequestParamForBean(arg0, MockService.class);
        mockServiceServiceImpl.deleteMockService(mockService);
        return selectMockService(arg0, false);
    }

}
