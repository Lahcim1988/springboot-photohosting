package com.company.springbootphotohosting.repo;

import com.company.springbootphotohosting.model.AppUser;
import com.company.springbootphotohosting.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Opcjonalnie możesz stworzyć Obiekt Image i ImageRepo (tak jak ja to zrobiłem),
// które umożliwią zapis i odczyt obrazka z bazy danych.

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
