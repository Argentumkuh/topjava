package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<MealWithExceed> test = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        test.forEach(System.out::println);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesMapPerDay =
            mealList.stream().collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(),
                    Collectors.summingInt(Meal::getCalories)));

        List<MealWithExceed> result =
                mealList.stream().filter(meal -> isInInterval(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(meal -> new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesMapPerDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());


        return result;
    }

    public static List<MealWithExceed> getFilteredWithExceededCycle(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<MealWithExceed> result = new ArrayList<>();
        Map<LocalDate, Integer> caloriesMapPerDay= new HashMap<>();

        for (Meal currentMeal :
                mealList) {
            caloriesMapPerDay.merge(currentMeal.getDateTime().toLocalDate(), currentMeal.getCalories(), Integer::sum);
        }

        for (Meal currentMeal :
                mealList) {
            if (isInInterval(currentMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                result.add(new MealWithExceed(currentMeal.getDateTime(), currentMeal.getDescription(), currentMeal.getCalories(),
                        caloriesMapPerDay.get(currentMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }

        return result;
    }

    private static boolean isInInterval(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return time.compareTo(startTime) >=0 && time.compareTo(endTime) <= 0;
    }
}
