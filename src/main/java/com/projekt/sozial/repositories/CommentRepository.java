package com.projekt.sozial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.sozial.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
