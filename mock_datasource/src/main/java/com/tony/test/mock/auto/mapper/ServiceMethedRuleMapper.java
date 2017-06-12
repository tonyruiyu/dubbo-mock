package com.tony.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tony.test.mock.po.ServiceMethedRule;
import com.tony.test.mock.po.ServiceMethedRuleExample;

public interface ServiceMethedRuleMapper {
    int countByExample(ServiceMethedRuleExample example);

    int deleteByExample(ServiceMethedRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceMethedRule record);

    int insertSelective(ServiceMethedRule record);

    List<ServiceMethedRule> selectByExampleWithBLOBs(ServiceMethedRuleExample example);

    List<ServiceMethedRule> selectByExample(ServiceMethedRuleExample example);

    ServiceMethedRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ServiceMethedRule record, @Param("example") ServiceMethedRuleExample example);

    int updateByExampleWithBLOBs(@Param("record") ServiceMethedRule record, @Param("example") ServiceMethedRuleExample example);

    int updateByExample(@Param("record") ServiceMethedRule record, @Param("example") ServiceMethedRuleExample example);

    int updateByPrimaryKeySelective(ServiceMethedRule record);

    int updateByPrimaryKeyWithBLOBs(ServiceMethedRule record);

    int updateByPrimaryKey(ServiceMethedRule record);
}