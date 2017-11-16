package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, 1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, 1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, 1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, 1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, 1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, 1)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> meals, int caloriesPerDay) {
        return getFilteredByTime(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<MealWithExceed> getFilteredByTime(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = getCaloriesMap(meals);

        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetweenTimes(meal.getTime(), startTime, endTime))
                .map(meal -> convertToWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    public static List<MealWithExceed> getFilteredByDate(Collection<Meal> meals, LocalDate startDate, LocalDate endDate, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumByDate = getCaloriesMap(meals);

        return meals.stream().filter(meal -> DateTimeUtil.isBetweenDates(meal.getDate(), startDate, endDate))
                .map(meal -> convertToWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    public static MealWithExceed convertToWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getUserId(), meal.getDateTime(), meal.getDescription(),
                meal.getCalories(), exceeded);
    }

    public static Meal convertToMeal (MealWithExceed mealWithExceed) {
        return new Meal(mealWithExceed.getId(), mealWithExceed.getUserId(), mealWithExceed.getDateTime(),
                mealWithExceed.getDescription(), mealWithExceed.getCalories());
    }

    private static Map<LocalDate, Integer> getCaloriesMap(Collection<Meal> meals) {
        return meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );
    }
}