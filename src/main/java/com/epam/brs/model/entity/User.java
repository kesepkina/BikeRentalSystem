package com.epam.brs.model.entity;

import com.epam.brs.model.entity.enumType.UserRole;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class User extends Entity{

    private int userId;
    private String login;
    private String name;
    private String surname;
    private String email;
    private UserRole role;
    private String photoName;
    private LocalDateTime dateOfRegistration;
    private boolean isBlocked;

    public User(String login, String name, String surname, String email) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User(int userId, String login, String name, String surname, String email) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User(String login, String name, String surname, String email, UserRole role) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public User(int userId, String login, String name, String surname, String email, UserRole role) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public User(int userId, String login, String name, String surname, String email, UserRole role, LocalDateTime dateOfRegistration, boolean isBlocked) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.dateOfRegistration = dateOfRegistration;
        this.isBlocked = isBlocked;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (isBlocked != user.isBlocked) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        if (photoName != null ? !photoName.equals(user.photoName) : user.photoName != null) return false;
        return dateOfRegistration != null ? dateOfRegistration.equals(user.dateOfRegistration) : user.dateOfRegistration == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (dateOfRegistration != null ? dateOfRegistration.hashCode() : 0);
        result = 31 * result + (isBlocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("login='" + login + "'")
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("email='" + email + "'")
                .add("role=" + role)
                .add("photoName='" + photoName + "'")
                .add("dateOfRegistration=" + dateOfRegistration)
                .add("isBlocked=" + isBlocked)
                .toString();
    }
}
