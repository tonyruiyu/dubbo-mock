package com.tony.mock;

import groovy.lang.Writable;
import groovy.text.SimpleTemplateEngine;
import groovy.text.TemplateEngine;

import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * 使用groovy模板引擎创建模板
 * 
 * @since V1.0
 * @version V1.0
 */
public class TemplateImpl implements Template {
	

    private final String  id;
    private final String  raw;
    private groovy.text.Template      template;
    private final boolean dynamic;

    /**
     * 构造函数
     * 
     * @param id
     * @param template
     */
    public TemplateImpl(String id, String template) {
        this.id = id;
        this.raw = template;

        this.dynamic = StringUtils.isNotBlank(raw) && (raw.contains("<%") || raw.contains("${"));

        if (dynamic) {
            try {
                this.template = TEMPLATE_ENGINE.createTemplate(template);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /** 
     * @throws Exception 
     */
    public String resolve(Map<String, ?> context) {
        try {
            return dynamic ? resolve(template, context) : raw;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to resolve template - " + id 
            		+ " - " + e.getMessage(), e);
        }
    }

    /** 
     */
    public String getId() {
        return id;
    }

    /**
     * 根据现有的模板对象，或者模板的raw字符串，创建新的模板
     * 
     * @param id
     * @param template
     * @param existing
     * @return
     */
    public static TemplateImpl create(String id, String template,
                                             TemplateImpl existing) {

        template = template == null ? "" : template;

        if (existing == null) {
            return new TemplateImpl(id, template);
        }
        if (!StringUtils.equals(template, existing.raw)) {
            return new TemplateImpl(id, template);
        }

        return existing;
    }

    private static final TemplateEngine TEMPLATE_ENGINE = new SimpleTemplateEngine();

    /**
     * 清除换行符
     * 
     * @param value
     * @return
     */
    private static String removeLineFeed(String value) {
        StringBuilder sb = new StringBuilder(value.length());
        for (char c : value.toCharArray()) {
            if (c != '\n' && c != '\r') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 模板解析
     * 
     * @param tmpl
     * @param context
     * @return
     * @throws Exception
     */
    private static String resolve(groovy.text.Template tmpl, Map<String, ?> context) throws Exception {

        Writable w = tmpl.make(context);

        return removeLineFeed(w.toString());

    }

    /** 
     */
    public String getRaw() {
        return raw;
    }

    /** 
     */
    public boolean isDynamic() {
        return dynamic;
    }

}
