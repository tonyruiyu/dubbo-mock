package com.tony.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tony.test.mock.po.MockTestParam;
import com.tony.test.mock.po.MockTestParamExample;

public interface MockTestParamMapper {
    int countByExample(MockTestParamExample example);

    int deleteByExample(MockTestParamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MockTestParam record);

    int insertSelective(MockTestParam record);

    List<MockTestParam> selectByExampleWithBLOBs(MockTestParamExample example);

    List<MockTestParam> selectByExample(MockTestParamExample example);

    MockTestParam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MockTestParam record, @Param("example") MockTestParamExample example);

    int updateByExampleWithBLOBs(@Param("record") MockTestParam record, @Param("example") MockTestParamExample example);

    int updateByExample(@Param("record") MockTestParam record, @Param("example") MockTestParamExample example);

    int updateByPrimaryKeySelective(MockTestParam record);

    int updateByPrimaryKeyWithBLOBs(MockTestParam record);

    int updateByPrimaryKey(MockTestParam record);
}