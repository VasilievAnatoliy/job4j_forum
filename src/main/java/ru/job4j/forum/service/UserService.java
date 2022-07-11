package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserMem;
import ru.job4j.forum.store.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public Optional<User> save(User user) {
        Optional rsl = Optional.empty();
        try {
            rsl = Optional.of(
            users.save(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public User findByUsername(String name) {
        return users.findByUsername(name);
    }
}
