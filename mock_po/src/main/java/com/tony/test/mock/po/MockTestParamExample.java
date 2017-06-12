package com.tony.test.mock.po;

import java.util.ArrayList;
import java.util.List;

public class MockTestParamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MockTestParamExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParKeyIsNull() {
            addCriterion("par_key is null");
            return (Criteria) this;
        }

        public Criteria andParKeyIsNotNull() {
            addCriterion("par_key is not null");
            return (Criteria) this;
        }

        public Criteria andParKeyEqualTo(String value) {
            addCriterion("par_key =", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyNotEqualTo(String value) {
            addCriterion("par_key <>", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyGreaterThan(String value) {
            addCriterion("par_key >", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyGreaterThanOrEqualTo(String value) {
            addCriterion("par_key >=", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyLessThan(String value) {
            addCriterion("par_key <", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyLessThanOrEqualTo(String value) {
            addCriterion("par_key <=", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyLike(String value) {
            addCriterion("par_key like", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyNotLike(String value) {
            addCriterion("par_key not like", value, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyIn(List<String> values) {
            addCriterion("par_key in", values, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyNotIn(List<String> values) {
            addCriterion("par_key not in", values, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyBetween(String value1, String value2) {
            addCriterion("par_key between", value1, value2, "parKey");
            return (Criteria) this;
        }

        public Criteria andParKeyNotBetween(String value1, String value2) {
            addCriterion("par_key not between", value1, value2, "parKey");
            return (Criteria) this;
        }

        public Criteria andParTypeIsNull() {
            addCriterion("par_type is null");
            return (Criteria) this;
        }

        public Criteria andParTypeIsNotNull() {
            addCriterion("par_type is not null");
            return (Criteria) this;
        }

        public Criteria andParTypeEqualTo(String value) {
            addCriterion("par_type =", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeNotEqualTo(String value) {
            addCriterion("par_type <>", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeGreaterThan(String value) {
            addCriterion("par_type >", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeGreaterThanOrEqualTo(String value) {
            addCriterion("par_type >=", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeLessThan(String value) {
            addCriterion("par_type <", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeLessThanOrEqualTo(String value) {
            addCriterion("par_type <=", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeLike(String value) {
            addCriterion("par_type like", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeNotLike(String value) {
            addCriterion("par_type not like", value, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeIn(List<String> values) {
            addCriterion("par_type in", values, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeNotIn(List<String> values) {
            addCriterion("par_type not in", values, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeBetween(String value1, String value2) {
            addCriterion("par_type between", value1, value2, "parType");
            return (Criteria) this;
        }

        public Criteria andParTypeNotBetween(String value1, String value2) {
            addCriterion("par_type not between", value1, value2, "parType");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNull() {
            addCriterion("subject_id is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Integer value) {
            addCriterion("subject_id =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Integer value) {
            addCriterion("subject_id <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Integer value) {
            addCriterion("subject_id >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("subject_id >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Integer value) {
            addCriterion("subject_id <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("subject_id <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Integer> values) {
            addCriterion("subject_id in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Integer> values) {
            addCriterion("subject_id not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Integer value1, Integer value2) {
            addCriterion("subject_id between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("subject_id not between", value1, value2, "subjectId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}