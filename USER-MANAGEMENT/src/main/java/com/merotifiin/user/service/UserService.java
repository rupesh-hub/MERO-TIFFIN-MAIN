package com.merotifiin.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.merotifiin.address.convertor.AddressConvertor;
import com.merotifiin.address.model.AddressPojo;
import com.merotifiin.global.pojo.Pagination;
import com.merotifiin.global.util.GlobalResponse;
import com.merotifiin.global.util.GlobalUtil;
import com.merotifiin.role.entity.Role;
import com.merotifiin.role.service.RoleService;
import com.merotifiin.user.convertor.UserConvertor;
import com.merotifiin.user.entity.User;
import com.merotifiin.user.mapper.UserMapper;
import com.merotifiin.user.model.UserPojo;
import com.merotifiin.user.model.UserRequestPojo;
import com.merotifiin.user.model.UserResponsePojo;
import com.merotifiin.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    private static final String USER_SAVED_SUCCESS = "user saved successfully [%s].";
    private static final String USER_FOUND_SUCCESS = "user fetch successfully.";
    private static final String USER_ROLE_ASSIGNED_SUCCESS = "role [%s] assigned to user [%s] successfully.";
    private static final String USER_ROLE_REMOVED_SUCCESS = "role [%s] removed for user [%s] successfully.";

    /**
     * register new user with address, role
     *
     * @param userRequestPojo
     * @return
     */
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

        return GlobalUtil
                .globalResponse(
                        String.format(USER_SAVED_SUCCESS, user.getUserId()),
                        HttpStatus.CREATED,
                        user.getUserId(),
                        null
                );
    }

    /**
     * get all user with pagination and search feature
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public GlobalResponse<List<UserResponsePojo>> allUser(final int page, final int limit) {

        final Page<UserResponsePojo> userResponsePage = userMapper.allUser(new Page(page, limit));

        return GlobalUtil
                .globalResponse(
                        String.format(USER_FOUND_SUCCESS),
                        HttpStatus.OK,
                        userResponsePage.getRecords(),
                        Pagination
                                .builder()
                                .page(userResponsePage.getCurrent())
                                .pageSize(userResponsePage.getSize())
                                .totalPages(userResponsePage.getPages())
                                .totalRecords(userResponsePage.getTotal())
                                .build()
                );
    }

    /**
     * assign and re-assign role to the user and return global response
     *
     * @param roleName
     * @param email
     * @return
     */
    @Override
    @Transactional
    public GlobalResponse<String> assignRole(final String roleName, final String email) {
        final User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(String.format("user by email [%s] not found.", email)));

        //TODO: validate role already assigned or not
        for (final Role role : user.getRoles()) {
            if(roleName.equalsIgnoreCase(role.getName().name()))
                throw new RuntimeException(String.format("role [%s] already assigned to user.", roleName));
        }

        final Role role = roleService.getByName(roleName);

        user.addRole(role);

        return GlobalUtil
                .globalResponse(
                        String.format(USER_ROLE_ASSIGNED_SUCCESS, roleName, email),
                        HttpStatus.CREATED,
                        user.getUserId(),
                        null
                );
    }

    /**
     * assigned role will be removed
     *
     * @param roleName
     * @param email
     * @return
     */
    @Override
    @Transactional
    public GlobalResponse<String> removeRole(final String roleName, final String email) {

        final User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(String.format("user by email [%s] not found.", email)));

        final Role role = roleService.getByName(roleName);
        user.removeRole(role);


        return GlobalUtil
                .globalResponse(
                        String.format(USER_ROLE_REMOVED_SUCCESS, roleName, email),
                        HttpStatus.CREATED,
                        user.getUserId(),
                        null
                );
    }

    /**
     * find user with user id
     *
     * @param userId
     * @return
     */
    @Override
    public GlobalResponse<UserPojo> getByUserId(final String userId) {

        return GlobalUtil
                .globalResponse(
                        String.format(USER_FOUND_SUCCESS),
                        HttpStatus.OK,
                        userRepository
                                .findByUserId(userId)
                                .map(UserConvertor::toPojo)
                                .orElseThrow(() ->
                                        new RuntimeException(String.format("user by user id [%s] not found.", userId))),
                        null
                );
    }

    /**
     * find user with email
     *
     * @param email
     * @return
     */
    @Override
    public GlobalResponse<UserPojo> getByEmail(final String email) {

        return GlobalUtil
                .globalResponse(
                        String.format(USER_FOUND_SUCCESS),
                        HttpStatus.OK,
                        userRepository
                                .findByEmail(email)
                                .map(UserConvertor::toPojo)
                                .orElseThrow(() ->
                                        new RuntimeException(String.format("user by email [%s] not found.", email))),
                        null
                );
    }

    /**
     * find user with phone number
     *
     * @param phone
     * @return
     */
    @Override
    public GlobalResponse<UserPojo> getByPhone(final String phone) {

        return GlobalUtil
                .globalResponse(
                        String.format(USER_FOUND_SUCCESS),
                        HttpStatus.OK,
                        userRepository
                                .findByPhone(phone)
                                .map(UserConvertor::toPojo)
                                .orElseThrow(() ->
                                        new RuntimeException(String.format("user by phone [%s] not found.", phone))),
                        null
                );
    }


}
