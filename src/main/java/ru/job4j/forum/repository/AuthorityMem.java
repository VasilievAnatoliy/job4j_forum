package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthorityMem {
    private final Map<Integer, Authority> authority = new ConcurrentHashMap<>();

    public AuthorityMem() {
        authority.put(1, Authority.of("ROLE_USER"));
        authority.put(2, Authority.of("ROLE_ADMIN"));
    }

    public Authority findByAuthority(String role) {
       return authority.values().stream()
               .filter(x -> role.equals(x.getAuthority()))
               .findAny()
               .orElse(null);
    }
}
