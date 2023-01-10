package com.merotifiin.user.convertor;

import com.merotifiin.user.entity.User;
import com.merotifiin.user.model.UserRequestPojo;

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

}
