package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal meal WHERE meal.id=:id AND meal.user.id=:user_id"),
        @NamedQuery(name = Meal.GET, query = "SELECT meal FROM Meal meal WHERE meal.id=:id AND meal.user.id=:user_id"),
        @NamedQuery(name = Meal.GET_All, query = "SELECT meal FROM Meal meal WHERE meal.user.id=:user_id ORDER BY meal.dateTime"),
        @NamedQuery(name = Meal.GET_ALL_BETWEEN, query = "SELECT meal FROM Meal meal WHERE meal.dateTime >=:startDate " +
                "AND meal.dateTime >=:endDate ORDER BY meal.dateTime"),
})
@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String GET = "Meal.get";
    public static final String GET_All = "Meal.getAll";
    public static final String GET_ALL_BETWEEN = "Meal.getAllBetween";

    @Column(name = "date_time", unique = true, nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    @NotNull
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
