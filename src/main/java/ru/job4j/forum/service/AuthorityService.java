package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityMem;

@Service
public class AuthorityService {
    private final AuthorityMem authority;

    public AuthorityService(AuthorityMem authority) {
        this.authority = authority;
    }

    public Authority findByAuthority(String role) {
        return authority.findByAuthority(role);
    }
}
