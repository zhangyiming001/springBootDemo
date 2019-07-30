package com.mapperInterface;

import entity.usertest;

public interface usertestMapper {
    int deleteByPrimaryKey(String id);

    int insert(usertest record);

    int insertSelective(usertest record);

    usertest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(usertest record);

    int updateByPrimaryKey(usertest record);
}