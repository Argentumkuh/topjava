package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Override
    @Transactional
    Meal save(Meal meal);

    @Transactional
    int deleteByIdAndUserId(int id, int userId);

    Meal findMealByIdAndUserId(int id, int userId);

    List<Meal> findMealByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> findMealByUserIdAndDateTimeBetweenOrderByDateTimeDesc
            (int userId, LocalDateTime startTime, LocalDateTime endTime);
}