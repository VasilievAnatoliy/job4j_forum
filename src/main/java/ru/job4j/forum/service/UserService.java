package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserMem;

import java.util.Collection;

@Service
public class UserService {
    private final UserMem users;

    public UserService(UserMem users) {
        this.users = users;
    }

    public User save(User user) {
       return users.save(user);
    }

    public User findById(int id) {
        return users.findById(id);
    }

    public User findByUsername(String name) {
        return users.findByUsername(name);
    }

    public Collection<User> findAll() {
        return users.findAll();
    }
}
