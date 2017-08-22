package org.demosoft.life.auth;

import org.demosoft.life.auth.model.CreateUserRequest;
import org.demosoft.life.auth.model.CreateUserResponse;
import org.demosoft.life.auth.model.LoginRequest;
import org.demosoft.life.auth.model.User;
import org.demosoft.life.auth.service.AuthService;
import org.demosoft.life.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.NoSuchAlgorithmException;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(UserService authService, AuthService authService1) {
        this.userService = authService;
        this.authService = authService1;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest createUserRequest,
                                                     UriComponentsBuilder uriComponentsBuilder) throws NoSuchAlgorithmException {
        CreateUserResponse user = userService.createUser(createUserRequest.encryptPassword());
        ResponseEntity.BodyBuilder created = ResponseEntity.created(uriComponentsBuilder.path("/auth/{id}").buildAndExpand(user.getId()).toUri());
        authService.login(new LoginRequest(createUserRequest.getLogin(), createUserRequest.getPassword(), false));
        return created.body(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }
}
