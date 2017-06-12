package com.tony.test.mock.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tony.test.mock.po.MockTestSubject;
import com.tony.test.mock.po.MockTestSubjectExample;

public interface MockTestSubjectMapper {
    int countByExample(MockTestSubjectExample example);

    int deleteByExample(MockTestSubjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MockTestSubject record);

    int insertSelective(MockTestSubject record);

    List<MockTestSubject> selectByExample(MockTestSubjectExample example);

    MockTestSubject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MockTestSubject record, @Param("example") MockTestSubjectExample example);

    int updateByExample(@Param("record") MockTestSubject record, @Param("example") MockTestSubjectExample example);

    int updateByPrimaryKeySelective(MockTestSubject record);

    int updateByPrimaryKey(MockTestSubject record);
}