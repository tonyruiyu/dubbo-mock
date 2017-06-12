package com.tony.test.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tony.test.ReflectUtil;
import com.tony.test.page.Page;
import com.tony.test.page.PageThreadLocal;

public class BaseController {
    protected <T> T assembleRequestParamForBean(HttpServletRequest request, Class<T> clazz) {
        return assembleRequestParamForBean(request, clazz, null);
    }

    protected Map<String, String> assembleRequestParamForMap(HttpServletRequest request) {
        return assembleRequestParamForMap(request, null);
    }

    protected List<Integer> assembleRequestParamForList(HttpServletRequest request) {
        List<Integer> list = Lists.newArrayList();
        Enumeration<String> eles = request.getParameterNames();
        while (eles.hasMoreElements()) {
            String key = eles.nextElement();
            if (StringUtils.isBlank(request.getParameter(key)) || "serviceId".equals(key)) {
                continue;
            }
            String[] values = request.getParameter(key).split(",");
            for (String key1 : values) {
                list.add(Integer.valueOf(key1));
            }
        }
        return list;
    }

    protected Map<String, String> assembleRequestParamForMap(HttpServletRequest request, Page<?> page) {
        Map<String, String> paraMap = Maps.newConcurrentMap();
        Enumeration<String> eles = request.getParameterNames();
        int pageNo = 1;
        int pageSize = 20;
        while (eles.hasMoreElements()) {
            String key = eles.nextElement();
            if (StringUtils.isBlank(request.getParameter(key))) {
                continue;
            }
            paraMap.put(key, request.getParameter(key));
        }
        if (page != null) {
            String pageNoStr = request.getParameter("pageNo");
            String pageSizeStr = request.getParameter("pageSize");
            pageNo = pageNoStr == null ? pageNo : Integer.valueOf(pageNoStr);
            pageSize = pageSizeStr == null ? pageSize : Integer.valueOf(pageSizeStr);
            assemblePage(page, pageNo, pageSize);
        }
        return paraMap;
    }

    protected <T> T assembleRequestParamForBean(HttpServletRequest request, Class<T> clazz, Page<?> page) {
        Map<String, String> paraMap = assembleRequestParamForMap(request, page);
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.populate(t, paraMap);
        } catch (Exception e) {
            throw new RuntimeException("MAP转换BEAN时出错。");
        }
        return t;
    }

    protected <T> T assembleRequestParamForBean(Map<String, String> paraMap, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.populate(t, paraMap);
            if (StringUtils.isNotBlank(paraMap.get("selectid"))) {
                ReflectUtil.setFieldValue(t, "id", Integer.valueOf(paraMap.get("selectid")));
            } else {
                ReflectUtil.setFieldValue(t, "id", null);
            }
        } catch (Exception e) {
            throw new RuntimeException("MAP转换BEAN时出错。");
        }
        return t;
    }

    private void assemblePage(Page<?> page, int pageNo, int pageSize) {
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        PageThreadLocal.setThreadLocalPage(page);
    }
}
