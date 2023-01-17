package com.merotifiin.user.service;

import com.merotiffin.shared.util.GlobalResponse;
import com.merotifiin.user.model.UserPojo;
import com.merotifiin.user.model.UserRequestPojo;
import com.merotifiin.user.model.UserResponsePojo;

import java.util.List;

public interface IUserService {

    GlobalResponse<String> createUser(final UserRequestPojo userRequestPojo);

    GlobalResponse<List<UserResponsePojo>> allUser(final int page, final int limit);

    GlobalResponse<String> assignRole(final String roleName, final String email);

    GlobalResponse<String> removeRole(final String roleName, final String email);

    GlobalResponse<UserPojo> getByUserId(final String userId);

    GlobalResponse<UserPojo> getByEmail(final String email);

    GlobalResponse<UserPojo> getByPhone(final String phone);

}
