package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMem;

import java.util.*;

@Service
public class PostService {
    private final PostMem posts;

    public PostService(PostMem posts) {
        this.posts = posts;
    }

    public void save(Post post) {
        posts.save(post);
    }

    public Collection<Post> getAll() {
        return posts.getAll();
    }

    public Post findById(int id) {
        return posts.findById(id);
    }

    public void deleteById(int id) {
        posts.deleteById(id);
    }

}
