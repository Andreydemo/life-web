package org.demosoft.life.auth.service;

import org.demosoft.life.auth.exception.UserExistException;
import org.demosoft.life.auth.model.CreateUserRequest;
import org.demosoft.life.auth.model.User;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MockUserStorage {

    private Map<String, User> byId = new ConcurrentHashMap<>();
    private Map<String, User> byLogin = new ConcurrentHashMap<>();

    public String add(CreateUserRequest user) throws NoSuchAlgorithmException {
        if (byLogin.get(user.getLogin()) != null) throw new UserExistException();
        User userToAdd = new User(user, UUID.randomUUID().toString());
        byId.put(userToAdd.getId(), userToAdd);
        byLogin.put(userToAdd.getLogin(), userToAdd);
        return userToAdd.getId();
    }

    public User getById(String id) {
        return byId.get(id);
    }

    public User getByLogin(String Login) {
        return byLogin.get(Login);
    }
}
