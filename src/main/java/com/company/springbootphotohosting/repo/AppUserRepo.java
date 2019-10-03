package com.company.springbootphotohosting.repo;

import com.company.springbootphotohosting.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Musimy uwzględnić też repozytorium, które umożliwi nam na zapis użytkownika do bazy danych.
// W repozytorium należy dodać metodę pozwalającą na zlokalizowanie użytkownika po jego nazwie.

@Repository
public interface AppUserRepo extends JpaRepository <AppUser, Long>{

        AppUser findByUsername(String username);
}
