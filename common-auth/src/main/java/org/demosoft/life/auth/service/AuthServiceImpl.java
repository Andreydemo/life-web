package org.demosoft.life.auth.service;

import org.demosoft.life.auth.exception.InvalidCredentioalsException;
import org.demosoft.life.auth.model.LoginRequest;
import org.demosoft.life.auth.model.User;
import org.demosoft.life.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User login(LoginRequest loginRequest) throws InvalidCredentioalsException {
        UsernamePasswordAuthenticationToken authentication1 = new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authentication1);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (User) authentication.getPrincipal();
}

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
