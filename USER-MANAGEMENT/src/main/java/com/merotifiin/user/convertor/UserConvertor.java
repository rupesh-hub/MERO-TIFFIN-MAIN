package com.merotifiin.user.convertor;

import com.merotifiin.user.entity.User;
import com.merotifiin.user.enums.Gender;
import com.merotifiin.user.model.UserPojo;
import com.merotifiin.user.model.UserRequestPojo;

import java.time.LocalDateTime;
import java.util.Date;

public class UserConvertor {

    public static User toEntity(final UserRequestPojo source) {
        final User user = new User();

        user.setFirstNameEn(source.getFirstNameEn());
        user.setMiddleNameEn(source.getMiddleNameEn());
        user.setLastNameEn(source.getLastNameEn());

        user.setFirstNameNp(source.getFirstNameNp());
        user.setMiddleNameNp(source.getMiddleNameNp());
        user.setLastNameNp(source.getLastNameNp());

        user.setEmail(source.getEmail());

        user.setGender(source.getGender());
        user.setDateOfBirth(source.getDateOfBirth());
        user.setPhone(source.getPhone());

        return user;
    }


    public static UserPojo toPojo(final User source) {
        final UserPojo userPojo = new UserPojo();

        userPojo.setUserId(source.getUserId());
        userPojo.setFullNameEn(fullNameEn(source));

        userPojo.setFullNameNp(fullNameNp(source));

        userPojo.setEmail(source.getEmail());

        userPojo.setGender(source.getGender());
        userPojo.setPhone(source.getPhone());

        userPojo.setLastLoginDate(source.getLastLoginDate());
        userPojo.setLastLogoutDate(source.getLastLogoutDate());

        userPojo.setCreatedBy(source.getCreatedBy());
        userPojo.setCreatedDate(source.getCreatedDate());

        userPojo.setModifiedBy(source.getModifiedBy());
        userPojo.setModifiedDate(source.getModifiedDate());

        userPojo.setIsActive(source.getIsActive());
        userPojo.setEnabled(source.isEnabled());

        return userPojo;
    }

    private static String fullNameEn(final User user) {
        String fullNameEn = null;

        if (!user.getMiddleNameEn().equals(""))
            fullNameEn = user.getFirstNameEn() + " " + user.getMiddleNameEn() + " " + user.getLastNameEn();
        else
            fullNameEn = user.getFirstNameEn() + " " + user.getLastNameEn();

        return fullNameEn;
    }

    private static String fullNameNp(final User user) {
        String fullNameNp = null;

        if (!user.getMiddleNameNp().equals(""))
            fullNameNp = user.getFirstNameNp() + " " + user.getMiddleNameNp() + " " + user.getLastNameNp();
        else
            fullNameNp = user.getFirstNameNp() + " " + user.getLastNameNp();

        return fullNameNp;
    }

}
