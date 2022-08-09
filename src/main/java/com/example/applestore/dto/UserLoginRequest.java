package com.example.applestore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor//解決每個類都一定要有一個無參數建構子的問題
public class UserLoginRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
