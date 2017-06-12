package com.tony.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tony.test.mock.po.RegistryConfig;
import com.tony.test.mock.po.RegistryConfigExample;

public interface RegistryConfigMapper {
    int countByExample(RegistryConfigExample example);

    int deleteByExample(RegistryConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RegistryConfig record);

    int insertSelective(RegistryConfig record);

    List<RegistryConfig> selectByExample(RegistryConfigExample example);

    RegistryConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RegistryConfig record, @Param("example") RegistryConfigExample example);

    int updateByExample(@Param("record") RegistryConfig record, @Param("example") RegistryConfigExample example);

    int updateByPrimaryKeySelective(RegistryConfig record);

    int updateByPrimaryKey(RegistryConfig record);
}