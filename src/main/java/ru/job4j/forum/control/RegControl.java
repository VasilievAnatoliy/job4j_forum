package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorityService;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserService users;
    private final AuthorityService authorities;

    public RegControl(PasswordEncoder encoder, UserService users,
                      AuthorityService authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        Optional<User> regUser = users.save(user);
        if (regUser.isEmpty()) {
            return "redirect:/reg?fail=true";
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", fail != null);
        return "reg";
    }
}
