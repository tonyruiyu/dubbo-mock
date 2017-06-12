package com.tony.test.mock.po;

public class MockTestSubject {
    private Integer id;

    private String testSubjectTitle;

    private Integer serviceid;

    private String method;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestSubjectTitle() {
        return testSubjectTitle;
    }

    public void setTestSubjectTitle(String testSubjectTitle) {
        this.testSubjectTitle = testSubjectTitle == null ? null : testSubjectTitle.trim();
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }
}