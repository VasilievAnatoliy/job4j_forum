package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMem {
    private final AuthorityMem authorityMem;

    public UserMem(AuthorityMem authorityMem) {
        this.authorityMem = authorityMem;

        save(User.of("Влад", "123",
                authorityMem.findByAuthority("USER"), true));
        save(User.of("Борис", "123",
                authorityMem.findByAuthority("USER"), true));
        save(User.of("Виталик", "123",
                authorityMem.findByAuthority("USER"), true));
    }

    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger();

    public User save(User user) {
        user.setId(count.incrementAndGet());
        users.put(user.getId(), user);
        return user;
    }

    public User findById(int id) {
        return users.get(id);
    }

    public User findByUsername(String name) {
        return users.values().stream()
                .filter(x -> name.equals(x.getUsername()))
                .findAny()
                .orElse(null);
    }

    public Collection<User> findAll() {
        return users.values();
    }
}
