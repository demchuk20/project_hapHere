package com.nulp.hapHere.repository;

import com.nulp.hapHere.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findAllByAuthorIdUser(Integer userID);
}
