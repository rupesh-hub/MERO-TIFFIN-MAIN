package com.merotifiin.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.merotifiin.user.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPojo {

    private String userId;
    private String firstNameEn;
    private String middleNameEn;
    private String lastNameEn;
    private String firstNameNp;
    private String middleNameNp;
    private String lastNameNp;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private Gender gender;
    private LocalDateTime lastLoginDate;
    private LocalDateTime lastLogoutDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private Boolean isActive;

}
