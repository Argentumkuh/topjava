package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(MealWithExceed meal) {
        return repository.save(MealsUtil.convertToMeal(meal));
    }

    @Override
    public void delete(int id) throws NotFoundException {
        if (isExist(id)) {
            repository.delete(id);
        } else throw new NotFoundException("Meal not found!");
    }

    @Override
    public MealWithExceed get(int id) throws NotFoundException {
        if (isExist(id)) {
            return MealsUtil.convertToWithExceed(repository.get(id), false);
        } else throw new NotFoundException("Meal not found!");
    }

    private boolean isExist(int id) {
        return repository.get(id) == null;
    }

    @Override
    public void update(MealWithExceed mealWithExceed) {
        if (isExist(mealWithExceed.getId())) {
            repository.save(MealsUtil.convertToMeal(mealWithExceed));
        } else throw new NotFoundException("Meal not found!");
    }

    @Override
    public List<MealWithExceed> getAll(Integer userId) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public List<MealWithExceed> filterByDate(Integer userId, LocalDate startDate, LocalDate endDate) {
        return MealsUtil.getFilteredByDate(repository.getAll(userId), startDate, endDate, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public List<MealWithExceed> filterByTime(Integer userId, LocalTime startTime, LocalTime endTime) {
        return MealsUtil.getFilteredByTime(repository.getAll(userId), startTime, endTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}