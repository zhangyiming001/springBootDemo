package mapperInterface;

import entity.UserTest;

public interface UserTestMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserTest record);

    int insertSelective(UserTest record);

    UserTest selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserTest record);

    int updateByPrimaryKey(UserTest record);
}