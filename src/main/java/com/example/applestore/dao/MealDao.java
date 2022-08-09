package com.example.applestore.dao;

import com.example.applestore.dto.MealRequest;
import com.example.applestore.model.Meal;

import java.util.List;

public interface MealDao {
    List<Meal> getMeals();
    Meal getMealById(Integer mealId);
    Integer createMeal(MealRequest mealRequest);
    void updateMeal(Integer mealId, MealRequest mealRequest);
    void deleteMealById(Integer mealId);
}
