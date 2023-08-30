package com.dmdev.spring.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

public record UserFilter(String firstname,
                         String lastname,
                         LocalDate birthDate) {

    @Override
    public String firstname() {
        return firstname;
    }

    @Override
    public String lastname() {
        return lastname;
    }

    @Override
    public LocalDate birthDate() {
        return birthDate;
    }
}
