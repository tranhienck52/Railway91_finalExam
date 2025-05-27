package com.data.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccountDto {
    private int id;
    private String username;
    private String password;
    private String role;
    private String email;
    private LocalDateTime createdAt;
    private Date birthday;
    private String address;
}
