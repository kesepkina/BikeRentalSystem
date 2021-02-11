package com.epam.brs.model.service;

public interface UserService {
    boolean checkUser(String username, String password);
    boolean checkLogin(String username);
    void addUser(String name, String email, String username, String password);
    String getName(String username);
    boolean isLogin(String usernameValue);
    boolean isPassword(String passwordValue);
    boolean isEmail(String emailValue);
}
