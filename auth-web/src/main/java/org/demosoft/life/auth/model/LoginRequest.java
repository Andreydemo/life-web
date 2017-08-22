package org.demosoft.life.auth.model;

import lombok.Value;
import org.demosoft.life.util.EncryptUtil;

@Value
public class LoginRequest {

    String name;
    String password;

    public LoginRequest(String name, String password) {
        this.name = name;
        this.password = EncryptUtil.hash(password);
    }

    public LoginRequest(String name, String password, boolean encrypted) {
        this.name = name;
        if (encrypted)
            this.password = EncryptUtil.hash(password);
        else
            this.password = password;
    }
}
