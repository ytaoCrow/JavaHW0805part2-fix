package com.example.applestore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor//解決每個類都一定要有一個無參數建構子的問題
public class MealRequest {

    @NotNull
    private String mealName;

    @NotNull
    private Integer price;

    private String description;
}
