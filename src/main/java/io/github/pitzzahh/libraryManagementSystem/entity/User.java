package io.github.pitzzahh.libraryManagementSystem.entity;

public class User<T> {

    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
