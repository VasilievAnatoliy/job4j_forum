package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserMem;
import ru.job4j.forum.store.UserRepository;

import java.util.Collection;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public User save(User user) {
       return users.save(user);
    }

    public User findByUsername(String name) {
        return users.findByUsername(name);
    }
}
