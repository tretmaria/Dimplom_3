package ru.yandex.praktikum.util;

import ru.yandex.praktikum.model.User;

public class Credentials {
    private String email;
    private String password;

    public Credentials() {
    }

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static Credentials from(User user) {
        return new Credentials(user.getEmail(), user.getPassword());
    }

    public Credentials setEmail(String email) {
        this.email = email;
        return this;
    }

    public Credentials setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "email='" + email + '\'' +
                '}';
    }
}
