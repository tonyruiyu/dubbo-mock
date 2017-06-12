package com.tony.test.mock.define.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tony.test.mock.po.MockServiceDefine;

public interface MockServiceDefineMapper {

    List<MockServiceDefine> selectMockServiceDefine(@Param(value = "mockServiceDefine") MockServiceDefine mockServiceDefine);

    List<String> selectMockRoleNames(@Param(value = "id") Integer id);
}
