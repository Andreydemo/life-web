package org.demosoft.life.auth.service;

import org.demosoft.life.auth.exception.InvalidCredentioalsException;
import org.demosoft.life.auth.model.LoginRequest;
import org.demosoft.life.auth.model.User;

public interface AuthService {

    User login(LoginRequest loginRequest) throws InvalidCredentioalsException;

    void logout();
}
