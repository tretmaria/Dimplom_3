package ru.yandex.praktikum.util;

import ru.yandex.praktikum.model.User;

public class Credentials {
    public String email;
    public String password;

    public Credentials() {
    }

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Credentials from(User user) {
        return new Credentials(user.email, user.password);
    }

    public Credentials setEmail(String email) {
        this.email = email;
        return this;
    }

    public Credentials setPassword(String password) {
        this.password = password;
        return this;
    }
}
