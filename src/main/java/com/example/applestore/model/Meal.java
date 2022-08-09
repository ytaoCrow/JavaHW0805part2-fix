package com.example.applestore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor//解決每個類都一定要有一個無參數建構子的問題

public class Meal {
    private Integer mealId;
    private String mealName;
    private Integer price;
    private String description;
    private Date createDate;
    private Date lastModifiedDate;
}
