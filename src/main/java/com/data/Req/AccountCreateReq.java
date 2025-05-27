package com.data.Req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccountCreateReq {
    @NotBlank(message = "Cần điền username")
    private String username;

    @NotBlank(message = "Cần điền password")
    private String password;

    @Email
    private String email;

    @NotBlank(message = "Cần điền địa chỉ")
    private String address;
}
