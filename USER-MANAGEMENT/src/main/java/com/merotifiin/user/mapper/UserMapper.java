package com.merotifiin.user.mapper;

import com.merotifiin.user.model.UserResponsePojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserResponsePojo> allUser();

}
