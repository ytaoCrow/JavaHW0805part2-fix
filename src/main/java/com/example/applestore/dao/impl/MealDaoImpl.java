package com.example.applestore.dao.impl;

import com.example.applestore.dao.MealDao;
import com.example.applestore.dto.MealRequest;
import com.example.applestore.model.Meal;
import com.example.applestore.rowmapper.MealRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MealDaoImpl implements MealDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Meal> getMeals() {
        String sql = "SELECT meal_id, meal_name,  price, description, created_date, last_modified_date " +
                "FROM meal";
        Map<String, Object> map = new HashMap<>();

        List<Meal> mealList = namedParameterJdbcTemplate.query(sql, map, new MealRowMapper());

        return mealList;
    }

    @Override
    public Meal getMealById(Integer mealId) {

        String sql = "SELECT meal_id, meal_name,  price, description, created_date, last_modified_date " +
                "FROM meal WHERE meal_id = :mealId";

        Map<String, Object> map = new HashMap<>();
        map.put("mealId", mealId);

        List<Meal> mealList = namedParameterJdbcTemplate.query(sql, map, new MealRowMapper());
        if(mealList.size()>0){
            return mealList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createMeal(MealRequest mealRequest) {
        String sql = "INSERT INTO meal (meal_name, price, description, created_date, last_modified_date) " +
                "VALUES (:mealName, :price, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("mealName", mealRequest.getMealName());
        map.put("price", mealRequest.getPrice());
        map.put("description", mealRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int mealId = keyHolder.getKey().intValue();

        return mealId;
    }

    @Override
    public void updateMeal(Integer mealId, MealRequest mealRequest) {
        String sql ="UPDATE meal SET meal_name = :mealName, price = :price, " +
                "description = :description, last_modified_date = :lastModifiedDate " +
                "WHERE meal_id = :mealId";

        Map<String, Object> map = new HashMap<>();
        map.put("mealId", mealId);

        map.put("mealName", mealRequest.getMealName());
        map.put("price", mealRequest.getPrice());
        map.put("description", mealRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteMealById(Integer mealId) {
        String sql = "DELETE FROM meal WHERE meal_id = :mealId";

        Map<String, Object> map = new HashMap<>();
        map.put("mealId", mealId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
