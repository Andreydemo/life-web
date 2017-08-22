package org.demosoft.life.auth;

import org.demosoft.life.auth.model.CommonUserDetails;
import org.demosoft.life.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class CommonAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserService authService;

    @Autowired
    public CommonAuthenticationProvider(UserService authService) {
        this.authService = authService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Assert.isTrue(userDetails.getPassword().equals(authentication.getCredentials()), "Invalid password");
    }


    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return new CommonUserDetails(authService.getUserByLogin(username));
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
