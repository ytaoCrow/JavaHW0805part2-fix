package com.example.applestore.controller;

import com.example.applestore.dto.MealRequest;
import com.example.applestore.model.Meal;
import com.example.applestore.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MealController {

    @Autowired
    private MealService mealService;
    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getMeals(){
       List<Meal> mealList = mealService.getMeals();

       return ResponseEntity.status(HttpStatus.OK).body(mealList);
    }

    @GetMapping("/meal/{mealId}")
    public ResponseEntity<Meal> getMeal(@PathVariable Integer mealId){
        Meal meal = mealService.getMealById(mealId);

        if (meal != null){
            return  ResponseEntity.status(HttpStatus.OK).body(meal);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/meal")
    public ResponseEntity<Meal> createMeal(@RequestBody @Valid MealRequest mealRequest){
      Integer mealId =  mealService.createMeal(mealRequest);

      Meal meal = mealService.getMealById(mealId);

      return ResponseEntity.status(HttpStatus.CREATED).body(meal);
    }

    @PutMapping("/meal/{mealId}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Integer mealId,
                                           @RequestBody @Valid MealRequest mealRequest){

        //先檢查是否有該商品？有才會執行下面的程式，沒有則回傳404
        Meal meal = mealService.getMealById(mealId);
        if (meal == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改商品數據
        mealService.updateMeal(mealId, mealRequest);

        Meal updateMeal = mealService.getMealById(mealId);

        return ResponseEntity.status(HttpStatus.OK).body(updateMeal);
    }

    @DeleteMapping("meal/{mealId}")
    public ResponseEntity<?> deleteMeal(@PathVariable Integer mealId){
        mealService.deleteMealById(mealId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
