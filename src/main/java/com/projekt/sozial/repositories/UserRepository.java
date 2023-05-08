package com.projekt.sozial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.sozial.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
