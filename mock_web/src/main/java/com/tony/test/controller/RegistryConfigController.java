package com.tony.test.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tony.test.mock.po.RegistryConfig;
import com.tony.test.page.Page;
import com.tony.test.service.RegistryConfigService;

@Controller public class RegistryConfigController extends BaseController {

    @Resource RegistryConfigService RegistryConfigServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/selectRegistryConfig")
    public ModelAndView selectRegistryConfig(HttpServletRequest arg0) throws Exception {
        Page<RegistryConfig> page = new Page<>();
        RegistryConfig RegistryConfig = assembleRequestParamForBean(arg0, RegistryConfig.class, page);
        List<RegistryConfig> results = RegistryConfigServiceImpl.selectRegistryConfig(RegistryConfig);
        page.setResults(results);
        page.setParams(RegistryConfig);
        ModelAndView view = new ModelAndView("jsp/selectRegistryConfig");
        view.addObject("page", page);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/updateOrInsertRegistryConfig")
    public RegistryConfig updateOrInsertRegistryConfig(HttpServletRequest arg0) throws Exception {
        RegistryConfig RegistryConfig = assembleRequestParamForBean(arg0, RegistryConfig.class);
        RegistryConfigServiceImpl.updateOrInsertRegistryConfig(RegistryConfig);
        return RegistryConfig;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRegistryConfig")
    public ModelAndView deleteRegistryConfig(HttpServletRequest arg0) throws Exception {
        RegistryConfig RegistryConfig = assembleRequestParamForBean(arg0, RegistryConfig.class);
        RegistryConfigServiceImpl.deleteRegistryConfig(RegistryConfig);
        return selectRegistryConfig(arg0);
    }

}
