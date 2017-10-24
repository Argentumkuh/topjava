package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> test = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed currentMeal :
                test) {
            System.out.println(currentMeal);
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> result = new ArrayList<>();
        Map<LocalDate, Integer> caloriesMapPerDay= new HashMap<>();

        for (UserMeal currentMeal :
                mealList) {
            if (isInInterval(currentMeal, startTime, endTime)) {
                result.add(new UserMealWithExceed(currentMeal.getDateTime(), currentMeal.getDescription(),
                        currentMeal.getCalories(), false));
                caloriesMapPerDay.merge(currentMeal.getDateTime().toLocalDate(), currentMeal.getCalories(), Integer::sum);
            }
        }

        for (UserMealWithExceed currentMeal :
                result) {
            if (caloriesMapPerDay.get(currentMeal.getDateTime().toLocalDate()) > caloriesPerDay) {
                currentMeal.setExceed(true);
            }
        }

        return result;
    }

    private static boolean isInInterval(UserMeal meal, LocalTime startTime, LocalTime endTime) {
        return meal.getDateTime().toLocalTime().isAfter(startTime) &&
                meal.getDateTime().toLocalTime().isBefore(endTime);
    }
}
