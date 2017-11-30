package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User user = manager.getReference(User.class, userId);
        meal.setUser(user);
            if (meal.isNew()) {
                manager.persist(meal);
                return meal;
            } else {
                if (get(meal.getId(), meal.getUser().getId()) != null) {
                    manager.merge(meal);
                    return meal;
                } else return null;
            }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return manager.createNamedQuery(Meal.DELETE, Meal.class)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> result = manager.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id" , id)
                .setParameter("user_id" , userId)
                .getResultList();
        return DataAccessUtils.singleResult(result);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return manager.createNamedQuery(Meal.GET_All, Meal.class).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return manager.createNamedQuery(Meal.GET_ALL_BETWEEN, Meal.class).getResultList();
    }
}