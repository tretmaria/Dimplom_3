package ru.yandex.praktikum.model;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class User {
    public String name;
    public String email;
    public String password;
    static Faker faker = new Faker();

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User getRandomUserCredentials(int chars) {
        final String name = faker.artist().name();
        final String email = faker.internet().emailAddress();
        final String password = RandomStringUtils.randomAlphabetic(chars);
        return new User(name, email, password);
    }
    public static User getUserEmail() {
        return new User().setEmail(faker.internet().emailAddress());
    }
    public static User getUserPassword(int chars) {
        return new User().setPassword(RandomStringUtils.randomAlphabetic(chars));
    }
    public static User getUserName() {
        return new User().setName(faker.artist().name());
    }


}
