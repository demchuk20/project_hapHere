package com.nulp.hapHere.repository;

import com.nulp.hapHere.entity.CreatedPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatedPostRepo extends JpaRepository<CreatedPost, Integer> {
    List<CreatedPost> findAllByModerated(Boolean moderated);
}
