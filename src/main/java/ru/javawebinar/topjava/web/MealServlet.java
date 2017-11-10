package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meal");

        List<MealWithExceed> model = MealsUtil.getFilteredWithExceeded(MealsUtil.meals,
                LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);

        request.setAttribute("model", model);

        request.getRequestDispatcher("/meal.jsp").forward(request, response);

    }

    public String getFormattedDate (LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd::MMM::uuuu HH::mm::ss"));
    }
}
