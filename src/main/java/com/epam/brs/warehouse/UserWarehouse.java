package com.epam.brs.warehouse;

import com.epam.brs.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserWarehouse {
    private static final UserWarehouse instance = new UserWarehouse();
    private List<User> users;

    private UserWarehouse() {
        users = new ArrayList<>();
        users.add(new User("Kseniya", "k.esepkina@gmail.com", "k.esepkina", "dD4$asdfzxcv"));
        users.add(new User("Ivan", "van32@gmail.com", "van32", "1234$asdfF"));
        users.add(new User("Лена", "lena@gmail.com", "lena1", "1234$asdfF"));
    }

    public static UserWarehouse getInstance() {
        return instance;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }

    public Optional<User> getUser(String username) {
        User user = null;
        int i = 0;
        while (i < users.size()) {
            if (users.get(i).getUsername().equals(username)) {
                user = users.get(i);
                break;
            }
            i++;
        }
        return Optional.ofNullable(user);
    }
}
