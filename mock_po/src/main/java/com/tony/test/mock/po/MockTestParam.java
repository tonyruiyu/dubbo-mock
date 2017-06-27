package com.tony.test.mock.po;

public class MockTestParam {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String parKey;

    /**
     * 
     */
    private String parValue;

    /**
     * 
     */
    private String parType;

    /**
     * 
     */
    private Integer subjectId;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return par_key 
     */
    public String getParKey() {
        return parKey;
    }

    /**
     * 
     * @param parKey 
     */
    public void setParKey(String parKey) {
        this.parKey = parKey == null ? null : parKey.trim();
    }

    /**
     * 
     * @return par_value 
     */
    public String getParValue() {
        return parValue;
    }

    /**
     * 
     * @param parValue 
     */
    public void setParValue(String parValue) {
        this.parValue = parValue == null ? null : parValue.trim();
    }

    /**
     * 
     * @return par_type 
     */
    public String getParType() {
        return parType;
    }

    /**
     * 
     * @param parType 
     */
    public void setParType(String parType) {
        this.parType = parType == null ? null : parType.trim();
    }

    /**
     * 
     * @return subject_id 
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * 
     * @param subjectId 
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}