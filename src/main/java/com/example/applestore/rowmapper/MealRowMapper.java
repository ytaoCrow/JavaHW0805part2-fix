package com.example.applestore.rowmapper;

import com.example.applestore.model.Meal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealRowMapper implements RowMapper<Meal> {
//數據轉換成java object
    @Override
    public Meal mapRow(ResultSet resultSet, int i) throws SQLException {
        Meal meal = new Meal();

        meal.setMealId(resultSet.getInt("meal_id"));
        meal.setMealName(resultSet.getString("meal_name"));
        meal.setPrice(resultSet.getInt("price"));
        meal.setDescription(resultSet.getString("description"));
        meal.setCreateDate(resultSet.getTimestamp("created_date"));
        meal.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return meal;
    }
}
