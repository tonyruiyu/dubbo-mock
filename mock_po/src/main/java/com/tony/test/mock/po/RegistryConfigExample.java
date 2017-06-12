package com.tony.test.mock.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistryConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegistryConfigExample() {
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

        public Criteria andRegistryProtocolIsNull() {
            addCriterion("registry_protocol is null");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolIsNotNull() {
            addCriterion("registry_protocol is not null");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolEqualTo(String value) {
            addCriterion("registry_protocol =", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolNotEqualTo(String value) {
            addCriterion("registry_protocol <>", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolGreaterThan(String value) {
            addCriterion("registry_protocol >", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolGreaterThanOrEqualTo(String value) {
            addCriterion("registry_protocol >=", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolLessThan(String value) {
            addCriterion("registry_protocol <", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolLessThanOrEqualTo(String value) {
            addCriterion("registry_protocol <=", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolLike(String value) {
            addCriterion("registry_protocol like", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolNotLike(String value) {
            addCriterion("registry_protocol not like", value, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolIn(List<String> values) {
            addCriterion("registry_protocol in", values, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolNotIn(List<String> values) {
            addCriterion("registry_protocol not in", values, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolBetween(String value1, String value2) {
            addCriterion("registry_protocol between", value1, value2, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryProtocolNotBetween(String value1, String value2) {
            addCriterion("registry_protocol not between", value1, value2, "registryProtocol");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressIsNull() {
            addCriterion("registry_address is null");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressIsNotNull() {
            addCriterion("registry_address is not null");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressEqualTo(String value) {
            addCriterion("registry_address =", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressNotEqualTo(String value) {
            addCriterion("registry_address <>", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressGreaterThan(String value) {
            addCriterion("registry_address >", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressGreaterThanOrEqualTo(String value) {
            addCriterion("registry_address >=", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressLessThan(String value) {
            addCriterion("registry_address <", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressLessThanOrEqualTo(String value) {
            addCriterion("registry_address <=", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressLike(String value) {
            addCriterion("registry_address like", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressNotLike(String value) {
            addCriterion("registry_address not like", value, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressIn(List<String> values) {
            addCriterion("registry_address in", values, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressNotIn(List<String> values) {
            addCriterion("registry_address not in", values, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressBetween(String value1, String value2) {
            addCriterion("registry_address between", value1, value2, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryAddressNotBetween(String value1, String value2) {
            addCriterion("registry_address not between", value1, value2, "registryAddress");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutIsNull() {
            addCriterion("registry_timeout is null");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutIsNotNull() {
            addCriterion("registry_timeout is not null");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutEqualTo(Integer value) {
            addCriterion("registry_timeout =", value, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutNotEqualTo(Integer value) {
            addCriterion("registry_timeout <>", value, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutGreaterThan(Integer value) {
            addCriterion("registry_timeout >", value, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("registry_timeout >=", value, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutLessThan(Integer value) {
            addCriterion("registry_timeout <", value, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("registry_timeout <=", value, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutIn(List<Integer> values) {
            addCriterion("registry_timeout in", values, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutNotIn(List<Integer> values) {
            addCriterion("registry_timeout not in", values, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("registry_timeout between", value1, value2, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andRegistryTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("registry_timeout not between", value1, value2, "registryTimeout");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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