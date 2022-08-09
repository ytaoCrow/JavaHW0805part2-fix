package com.example.applestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor//解決每個類都一定要有一個無參數建構子的問題

public class User {
    private Integer userId;
    @JsonProperty("e_mail")//json格式email -> e_mail
    private String email;
    @JsonIgnore
    private String password;//不會回傳密碼給前端
    private Integer phone_number;
    private Date createDate;
    private Date lastModifiedDate;

}
