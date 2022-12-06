package io.github.pitzzahh.libraryManagementSystem.entity;

public class User<T> {

    private final String username;
    private final T user;

    public User(T user, String username) {
        this.user = user;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public T getUser() {
        return user;
    }
}
