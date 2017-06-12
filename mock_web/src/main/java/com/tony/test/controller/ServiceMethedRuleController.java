package com.tony.test.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tony.test.mock.po.ServiceMethedRule;
import com.tony.test.page.Page;
import com.tony.test.service.MockOperDefineService;
import com.tony.test.service.ServiceMethedRuleService;

@Controller public class ServiceMethedRuleController extends BaseController {

    @Resource ServiceMethedRuleService ServiceMethedRuleServiceImpl;
    
    @Resource MockOperDefineService mockOperDefineServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/selectServiceMethedRule")
    public ModelAndView selectServiceMethedRule(HttpServletRequest arg0) throws Exception {
        Page<ServiceMethedRule> page = new Page<>();
        ServiceMethedRule ServiceMethedRule = assembleRequestParamForBean(arg0, ServiceMethedRule.class, page);
        List<ServiceMethedRule> results = ServiceMethedRuleServiceImpl.selectServiceMethedRule(ServiceMethedRule);
        page.setResults(results);
        page.setParams(ServiceMethedRule);
        ModelAndView view = new ModelAndView("jsp/selectServiceMethedRule");
        view.addObject("page", page);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/updateOrInsertServiceMethedRule")
    public ServiceMethedRule updateOrInsertServiceMethedRule(HttpServletRequest arg0) throws Exception {
        ServiceMethedRule serviceMethedRule = assembleRequestParamForBean(arg0, ServiceMethedRule.class);
        ServiceMethedRuleServiceImpl.updateOrInsertServiceMethedRule(serviceMethedRule);
        return serviceMethedRule;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteServiceMethedRule")
    public ServiceMethedRule deleteServiceMethedRule(HttpServletRequest arg0) throws Exception {
        ServiceMethedRule serviceMethedRule = assembleRequestParamForBean(arg0, ServiceMethedRule.class);
        ServiceMethedRuleServiceImpl.deleteServiceMethedRule(serviceMethedRule);
        return serviceMethedRule;
    }

}
