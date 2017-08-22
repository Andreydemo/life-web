package org.demosoft.life.auth.service;

import org.demosoft.life.auth.exception.UserExistException;
import org.demosoft.life.auth.model.CreateUserRequest;
import org.demosoft.life.auth.model.CreateUserResponse;
import org.demosoft.life.auth.model.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws UserExistException, NoSuchAlgorithmException;

    User getUserById(String id);

    User getUserByLogin(String login);
}
