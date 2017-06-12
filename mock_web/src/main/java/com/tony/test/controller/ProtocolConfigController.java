package com.tony.test.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tony.test.mock.po.ProtocolConfig;
import com.tony.test.page.Page;
import com.tony.test.service.ProtocolConfigService;

@Controller public class ProtocolConfigController extends BaseController {

    @Resource ProtocolConfigService ProtocolConfigServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/selectProtocolConfig")
    public ModelAndView selectProtocolConfig(HttpServletRequest arg0) throws Exception {
        Page<ProtocolConfig> page = new Page<>();
        ProtocolConfig ProtocolConfig = assembleRequestParamForBean(arg0, ProtocolConfig.class, page);
        List<ProtocolConfig> results = ProtocolConfigServiceImpl.selectProtocolConfig(ProtocolConfig);
        page.setResults(results);
        page.setParams(ProtocolConfig);
        ModelAndView view = new ModelAndView("jsp/selectProtocolConfig");
        view.addObject("page", page);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/updateOrInsertProtocolConfig")
    public ProtocolConfig updateOrInsertProtocolConfig(HttpServletRequest arg0) throws Exception {
        ProtocolConfig ProtocolConfig = assembleRequestParamForBean(arg0, ProtocolConfig.class);
        ProtocolConfigServiceImpl.updateOrInsertProtocolConfig(ProtocolConfig);
        return ProtocolConfig;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProtocolConfig")
    public ModelAndView deleteProtocolConfig(HttpServletRequest arg0) throws Exception {
        ProtocolConfig ProtocolConfig = assembleRequestParamForBean(arg0, ProtocolConfig.class);
        ProtocolConfigServiceImpl.deleteProtocolConfig(ProtocolConfig);
        return selectProtocolConfig(arg0);
    }

}
