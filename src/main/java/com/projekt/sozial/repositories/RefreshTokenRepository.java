package com.projekt.sozial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.sozial.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

}
