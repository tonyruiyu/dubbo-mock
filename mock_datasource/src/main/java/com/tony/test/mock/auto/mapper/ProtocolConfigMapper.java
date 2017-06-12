package com.tony.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tony.test.mock.po.ProtocolConfig;
import com.tony.test.mock.po.ProtocolConfigExample;

public interface ProtocolConfigMapper {
    int countByExample(ProtocolConfigExample example);

    int deleteByExample(ProtocolConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProtocolConfig record);

    int insertSelective(ProtocolConfig record);

    List<ProtocolConfig> selectByExample(ProtocolConfigExample example);

    ProtocolConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProtocolConfig record, @Param("example") ProtocolConfigExample example);

    int updateByExample(@Param("record") ProtocolConfig record, @Param("example") ProtocolConfigExample example);

    int updateByPrimaryKeySelective(ProtocolConfig record);

    int updateByPrimaryKey(ProtocolConfig record);
}