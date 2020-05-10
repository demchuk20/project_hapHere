package com.nulp.hapHere.controller;

import com.nulp.hapHere.entity.Post;
import com.nulp.hapHere.entity.User;
import com.nulp.hapHere.logger.Log;
import com.nulp.hapHere.repository.CreatedPostRepo;
import com.nulp.hapHere.repository.PostRepo;
import com.nulp.hapHere.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    private UserRepo userRepo;
    private CreatedPostRepo createdPostRepo;
    private PostRepo postRepo;

    @Autowired
    public UserController(UserRepo userRepo, CreatedPostRepo createdPostRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.createdPostRepo = createdPostRepo;
        this.postRepo = postRepo;
    }

    @Log
    @GetMapping("/user")
    public String hello(Principal principal, Model model) {
        User user = userRepo.findByEmail(principal.getName()).get();
        List<Post> list = postRepo.findAllByAuthorIdUser(user.getIdUser());
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "user";
    }

    @Log
    @GetMapping("/add-post")
    public String addAPost() {
        return "add-post";
    }

    @Log
    @PostMapping("/add-post")
    public String addPost(@RequestParam("file") MultipartFile file,
                          @RequestParam("tittle") String tittle,
                          @RequestBody String text,
                          @RequestParam("category") String category,
                          @RequestParam("date") String date,
                          Principal principal) throws IOException {
        Post post = new Post();
        if (file != null) {
            String fileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            file.transferTo(new File("F:\\Java.projects\\Курсова ПІС\\hap-here\\src\\main\\resources\\static\\img\\" + fileName));
            post.setImg(fileName);
        }
        post.setTitle(tittle);
        post.setText(text);
        post.setCategory(category);
        post.setDate(Timestamp.valueOf(date));
        post.setAuthor(userRepo.findByEmail(principal.getName()).get());
        postRepo.save(post);
        return "home";
    }
}
