package org.demosoft.life.auth.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.NonFinal;
import org.demosoft.life.util.EncryptUtil;

import java.io.Serializable;

@Data
@NonFinal
@Setter(AccessLevel.PROTECTED)
public class User implements Serializable {

    String id;
    String login;
    String email;
    String password;

    public User() {
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;

        this.password = password;
        id = null;
    }

    public User(String login, String email, String password, String id) {
        this.login = login;
        this.email = email;

        this.password = password;

        this.id = id;
    }

    public User(User user, String id) {
        this.login = user.login;
        this.email = user.getEmail();

        this.password = user.password;

        this.id = id;
    }

    public User encryptPassword() {
        this.password = EncryptUtil.hash(this.password);
        return this;
    }

}
