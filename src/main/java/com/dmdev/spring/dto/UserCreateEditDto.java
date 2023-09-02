package com.dmdev.spring.dto;

import com.dmdev.spring.database.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    @Email
    String username;
    //     // # second var decide problem with convert date from string in MVC test
//     @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @NotNull
    @Size(min = 3, max = 64)
    String firstname;

    @NotNull
    String lastname;

    Role role;

    Integer companyId;

}
