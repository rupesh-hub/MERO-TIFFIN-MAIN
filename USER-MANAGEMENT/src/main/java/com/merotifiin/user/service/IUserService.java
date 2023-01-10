package com.merotifiin.user.service;

import com.merotifiin.global.util.GlobalResponse;
import com.merotifiin.user.model.UserRequestPojo;
import com.merotifiin.user.model.UserResponsePojo;

import java.util.List;

public interface IUserService {

    GlobalResponse<String> createUser(final UserRequestPojo userRequestPojo);
    GlobalResponse<List<UserResponsePojo>> allUser();

}
