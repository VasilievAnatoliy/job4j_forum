package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {
    private final UserMem userMem;

    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger count = new AtomicInteger();


    public PostMem(UserMem userMem) {
        this.userMem = userMem;

        save(Post.of("Продаю машину ладу 01.",
                "Описание авто", userMem.findById(1))
                .addComment(Comment.of("Куплю за 80.000р.", userMem.findById(2)))
                .addComment(Comment.of("Фото есть?", userMem.findById(3))));

        save(Post.of("Куплю шины летние R15/185/65 б/у",
                "мало б/у недорого", userMem.findById(2)));
        save(Post.of("Ремонт бытовой техники",
                "Ремонтируем любую бытовую технику. Выезд бесплатно",
                userMem.findById(3)));
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(count.incrementAndGet());
            post.setCreated(Calendar.getInstance());
            post.setUser(userMem.findByUsername(post.getUser().getUsername()));

        } else {
            Post oldPost = posts.get(post.getId());
            post.setCreated(oldPost.getCreated());
            post.setComments(oldPost.getComments());
            post.setUser(oldPost.getUser());
        }
        posts.put(post.getId(), post);
    }

    public Collection<Post> getAll() {
        return posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void deleteById(int id) {
        posts.remove(id);
    }
}
