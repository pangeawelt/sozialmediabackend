package com.projekt.sozial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projekt.sozial.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByUserId(Long userId);//bir satir secmek icin
//bu metodun amacı, verilen userId değerine sahip olan kullanıcının
// en son yenileme anahtarını (RefreshToken) bulmaktır
    //Der Zweck dieser Methode war es, den letzten Aktualisierungsschlüssel (RefreshToken) des Benutzers
    // mit der angegebenen Benutzer-ID zu finden
}
