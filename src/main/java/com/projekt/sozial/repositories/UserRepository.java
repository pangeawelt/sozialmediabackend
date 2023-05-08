package com.projekt.sozial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.sozial.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD
    User findByUserName(String userName);
=======

>>>>>>> 59c9e6d57498fa95afedc271903fd34e6b0da23d
}
