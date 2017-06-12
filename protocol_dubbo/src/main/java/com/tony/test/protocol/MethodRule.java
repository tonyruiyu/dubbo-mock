package com.tony.test.protocol;

import java.util.List;
import java.util.Map;

import com.tony.mock.Template;
import com.tony.mock.TemplateImpl;
import com.tony.test.mock.po.MockService;
import com.tony.test.mock.po.ServiceMethedRule;

public class MethodRule {

    private List<ServiceMethedRule> serviceMethedRules;

    private String                  methodName;

    private MockService             service;

    private Template                template;

    private DataCountPointer        dataCountPointer;

    public MethodRule(MockService service, String methodName, List<ServiceMethedRule> serviceMethedRules) {
        super();
        this.service = service;
        this.methodName = methodName;
        this.serviceMethedRules = serviceMethedRules;
        this.dataCountPointer = new DataCountPointer();
        buildTemlate();
    }

    private void buildTemlate() {
        String context = buildScript();
        this.template = new TemplateImpl(service.getId() + "-" + methodName, context);
    }

    private String buildScript() {
        StringBuilder scriptContext = new StringBuilder("<%\n");
        for (int i = 0; i < serviceMethedRules.size(); i++) {
            String when = serviceMethedRules.get(i).getWhenScript();
            String thenMessage = serviceMethedRules.get(i).getReturnMessage();
            scriptContext.append("if( ").append(when).append(") {").append("print \"").append(removeLine(thenMessage)).append("\";\n return;}\n ");
        }
        scriptContext.append("print null;\n");
        scriptContext.append(" %>");
        return scriptContext.toString();
    }

    private String removeLine(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (char ch : str.toCharArray()) {
            if ('\r' == ch || '\n' == ch) {
                continue;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public String resolve(Map<String, Object> context) {
        return template.resolve(context);
    }

    public List<ServiceMethedRule> getServiceMethedRules() {
        return serviceMethedRules;
    }

    public String getMethodName() {
        return methodName;
    }

    public MockService getService() {
        return service;
    }

    public Template getTemplate() {
        return template;
    }

    public DataCountPointer getDataCountPointer() {
        return dataCountPointer;
    }
}
