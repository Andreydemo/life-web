package org.demosoft.life.auth.service;

import org.demosoft.life.auth.exception.UserExistException;
import org.demosoft.life.auth.model.CreateUserRequest;
import org.demosoft.life.auth.model.CreateUserResponse;
import org.demosoft.life.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Component
public class UserServiceImpl implements UserService {

    private final MockUserStorage mockUserStorage;

    @Autowired
    public UserServiceImpl(MockUserStorage mockUserStorage) {
        this.mockUserStorage = mockUserStorage;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws UserExistException, NoSuchAlgorithmException {
        return new CreateUserResponse(mockUserStorage.add(createUserRequest));
    }

    @Override
    public User getUserById(String id) {
        return mockUserStorage.getById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return mockUserStorage.getByLogin(login);
    }
}
