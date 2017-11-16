package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {

    private final MealService service;

    private final Integer authorizedUserId = AuthorizedUser.id();

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAll() {
        return service.getAll(authorizedUserId);
    }

    public List<MealWithExceed> getByTime(LocalTime startTime, LocalTime endTime) {
        return service.filterByTime(authorizedUserId, startTime, endTime);
    }

    public List<MealWithExceed> getByDate(LocalDate startDate, LocalDate endDate) {
        return service.filterByDate(authorizedUserId, startDate, endDate);
    }
 }