package ru.job4j.forum.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String description;
    private Calendar created;
    private User user;
    private List<Comment> comments;

    public static Post of(String name, String description,
                          User user) {
        Post post = new Post();
        post.name = name;
        post.description = description;
        post.user = user;
        return post;
    }

    public Post addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comment.setCreated(Calendar.getInstance());
        this.comments.add(comment);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(name, post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }
}