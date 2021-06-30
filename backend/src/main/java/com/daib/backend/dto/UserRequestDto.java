package com.daib.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class UserRequestDto {

    private String username;

    private String password;

    private String name;
}
