package ru.job4j.forum.control;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class PostControl {
    private final PostService postService;
    private final UserService userService;

    public PostControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post/{postId}")
    public String descriptionPost(
            Model model, @PathVariable("postId") int id,
            @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/createPost")
    public String create(Model model, @ModelAttribute Post post) {
        model.addAttribute("post", post);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "edit";
    }

    @GetMapping("/updatePost/{postId}")
    public String update(Model model,
                         @PathVariable("postId") int id) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        post.setUser(userService.findByUsername(post.getUser().getUsername()));
        postService.save(post);
        return "redirect:/index";
    }

    @GetMapping("/deletePost/{postId}")
    public String delete(@PathVariable("postId") int id) {
        postService.deleteById(id);
        return "redirect:/index";
    }

   @PostMapping("/saveComment")
   public String saveComment(@ModelAttribute Comment comment,
                             @RequestParam("postId") int id) {
       Post post = postService.findById(id);
       comment.setUser(userService.findByUsername(comment.getUser().getUsername()));
       post.addComment(comment);
       postService.save(post);
       return "redirect:/post/" + id;
   }
}
