package com.country.queue.mapper;

import com.country.queue.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findUser(@Param("useName") String useName);
}
