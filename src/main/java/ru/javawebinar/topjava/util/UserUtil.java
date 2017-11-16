package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User(null, "User1", "111@111.com", "111", Role.ROLE_ADMIN, Role.ROLE_USER));
        USERS.add(new User(null, "User2", "222@222.com", "222", Role.ROLE_USER));
        USERS.add(new User(null, "User3", "333@333.com", "333", Role.ROLE_ADMIN));
        USERS.add(new User(null, "User4", "444@444.com", "444", Role.ROLE_USER));
    }

}
