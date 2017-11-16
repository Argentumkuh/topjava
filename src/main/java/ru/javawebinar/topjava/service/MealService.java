package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal create(MealWithExceed mealWithExceed);

    void delete(int id) throws NotFoundException;

    MealWithExceed get(int id) throws NotFoundException;

    void update(MealWithExceed mealWithExceed);

    List<MealWithExceed> getAll(Integer userId);

    List<MealWithExceed> filterByDate(Integer userId, LocalDate startDate, LocalDate endDate);

    List<MealWithExceed> filterByTime(Integer userId, LocalTime startTime, LocalTime endTime);
}