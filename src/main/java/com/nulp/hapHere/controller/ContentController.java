package com.nulp.hapHere.controller;

import com.nulp.hapHere.entity.Post;
import com.nulp.hapHere.logger.Log;
import com.nulp.hapHere.repository.CreatedPostRepo;
import com.nulp.hapHere.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ContentController {
    private CreatedPostRepo createdPostRepo;
    private PostRepo postRepo;

    @Autowired
    public ContentController(CreatedPostRepo createdPostRepo, PostRepo postRepo) {
        this.createdPostRepo = createdPostRepo;
        this.postRepo = postRepo;
    }

    @Log
    @GetMapping("/")
    public String home(Model model) {
        List<List<Post>> listList = new ArrayList<>();
        List<Post> list = createdPostRepo.findAllByModerated(true)
                .stream()
                .map((createdPost -> createdPost.getPost()))
                .collect(Collectors.toList());
        int start = 0;
        int end = 3;
        do {
            listList.add(list.subList(start, end));
            start = end;
            end += 3;
        } while (start < list.size());
        model.addAttribute("lists", listList);
        return "home";
    }
}
