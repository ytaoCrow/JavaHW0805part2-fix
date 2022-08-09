package com.example.applestore.service.impl;

import com.example.applestore.dao.MealDao;
import com.example.applestore.dto.MealRequest;
import com.example.applestore.model.Meal;
import com.example.applestore.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MealServiceImpl implements MealService {

    @Autowired
    private MealDao mealDao;

    @Override
    public List<Meal> getMeals() {
        return mealDao.getMeals();
    }

    @Override
    public Meal getMealById(Integer mealId) {
        return mealDao.getMealById(mealId);
    }

    @Override
    public Integer createMeal(MealRequest mealRequest) {
        return mealDao.createMeal(mealRequest);
    }

    @Override
    public void updateMeal(Integer mealId, MealRequest mealRequest)
    {
        mealDao.updateMeal(mealId, mealRequest);
    }

    @Override
    public void deleteMealById(Integer mealId) {
        mealDao.deleteMealById(mealId);
    }


}
