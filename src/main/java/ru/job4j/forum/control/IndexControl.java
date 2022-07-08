package ru.job4j.forum.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

@Controller
public class IndexControl {
    private final PostService posts;

    public IndexControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model,
                        @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("posts", posts.getAll());
        return "index";
    }
}
