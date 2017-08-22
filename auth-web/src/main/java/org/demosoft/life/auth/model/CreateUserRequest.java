package org.demosoft.life.auth.model;

import lombok.Data;
import org.demosoft.life.util.EncryptUtil;

import java.security.NoSuchAlgorithmException;

@Data
public class CreateUserRequest extends User {

    String confirmPassword;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String login, String email, String password, String confirmPassword) throws NoSuchAlgorithmException {
        super(login, email, password);
        this.confirmPassword = confirmPassword;
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(EncryptUtil.hash(password));
    }

    @Override
    public CreateUserRequest encryptPassword() {
        super.encryptPassword();
        this.confirmPassword = EncryptUtil.hash(confirmPassword);
        return this;
    }
}
