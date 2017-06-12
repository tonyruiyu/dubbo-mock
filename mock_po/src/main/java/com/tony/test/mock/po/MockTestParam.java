package com.tony.test.mock.po;

public class MockTestParam {
    private Integer id;

    private String parKey;

    private String parType;

    private Integer subjectId;

    private String parValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParKey() {
        return parKey;
    }

    public void setParKey(String parKey) {
        this.parKey = parKey == null ? null : parKey.trim();
    }

    public String getParType() {
        return parType;
    }

    public void setParType(String parType) {
        this.parType = parType == null ? null : parType.trim();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getParValue() {
        return parValue;
    }

    public void setParValue(String parValue) {
        this.parValue = parValue == null ? null : parValue.trim();
    }
}