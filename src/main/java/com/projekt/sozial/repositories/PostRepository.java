package com.projekt.sozial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.sozial.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
