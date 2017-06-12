package com.tony.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tony.test.mock.po.MockService;
import com.tony.test.mock.po.MockServiceExample;

public interface MockServiceMapper {
    int countByExample(MockServiceExample example);

    int deleteByExample(MockServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MockService record);

    int insertSelective(MockService record);

    List<MockService> selectByExample(MockServiceExample example);

    MockService selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MockService record, @Param("example") MockServiceExample example);

    int updateByExample(@Param("record") MockService record, @Param("example") MockServiceExample example);

    int updateByPrimaryKeySelective(MockService record);

    int updateByPrimaryKey(MockService record);
}