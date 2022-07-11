package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityMem;
import ru.job4j.forum.store.AuthorityRepository;

@Service
public class AuthorityService {
    private final AuthorityRepository authority;

    public AuthorityService(AuthorityRepository authority) {
        this.authority = authority;
    }

    public Authority findByAuthority(String role) {
        return authority.findByAuthority(role);
    }
}
