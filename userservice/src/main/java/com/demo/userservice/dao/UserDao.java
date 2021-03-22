package com.demo.userservice.dao;

import com.demo.vo.user.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    @Insert("insert into user(name,age) values(#{name},#{age})")
    public void createUser(UserVo userVo);

    @Select("select * from user where id>#{id} limit 100")
    public List<UserVo> getAlluserList(Integer id);
}
