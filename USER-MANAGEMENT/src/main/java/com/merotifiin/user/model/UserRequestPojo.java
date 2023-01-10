package com.merotifiin.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.merotifiin.address.model.AddressPojo;
import com.merotifiin.user.enums.Gender;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestPojo {

    private String firstNameEn;
    private String middleNameEn;
    private String lastNameEn;
    private String firstNameNp;
    private String middleNameNp;
    private String lastNameNp;
    private String email;
    private String phone;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Gender gender;
    private AddressPojo address;
    private List<String> roles;

}
