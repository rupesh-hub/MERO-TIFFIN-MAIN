package com.merotifiin.user.service;

import com.merotifiin.address.convertor.AddressConvertor;
import com.merotifiin.address.model.AddressPojo;
import com.merotifiin.global.util.GlobalResponse;
import com.merotifiin.global.util.GlobalUtil;
import com.merotifiin.role.entity.Role;
import com.merotifiin.role.service.RoleService;
import com.merotifiin.user.convertor.UserConvertor;
import com.merotifiin.user.entity.User;
import com.merotifiin.user.mapper.UserMapper;
import com.merotifiin.user.model.UserRequestPojo;
import com.merotifiin.user.model.UserResponsePojo;
import com.merotifiin.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private static final String USER_SAVED_SUCCESS = "user saved successfully [%s].";
    private static final String USER_FOUND_SUCCESS = "user fetch successfully.";

    @Override
    @Transactional
    public GlobalResponse<String> createUser(final UserRequestPojo userRequestPojo) {

        User user = UserConvertor.toEntity(userRequestPojo);
        final String userId = GlobalUtil.randomString();
        user.setUserId(userId);
        user.setPassword(GlobalUtil.randomString());
        user.setCreatedBy(userId);
        user.setCreatedDate(GlobalUtil.currentDate());

        //TODO: validation - user exists by userId, email, phone

        //TODO: save address
        final AddressPojo addressPojo = userRequestPojo.getAddress();
        addressPojo.setCreatedDate(GlobalUtil.currentDate());
        addressPojo.setCreatedBy(userId);

        user.setAddress(AddressConvertor.toEntity(addressPojo));

        //TODO: save role
        final User finalUser = user;

        userRequestPojo.getRoles().forEach(roleName -> {
            finalUser.addRole(roleService.getByName(roleName));
        });

        user = userRepository.save(finalUser);

        return GlobalUtil
                .globalResponse(
                        String.format(USER_SAVED_SUCCESS, user.getUserId()),
                        HttpStatus.CREATED,
                        user.getUserId()
                );
    }

    @Override
    public GlobalResponse<List<UserResponsePojo>> allUser() {
        return GlobalUtil
                .globalResponse(
                        String.format(USER_FOUND_SUCCESS),
                        HttpStatus.OK,
                        userMapper.allUser()
                );
    }
}
