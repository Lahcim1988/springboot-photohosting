package com.company.springbootphotohosting.repo;

import com.company.springbootphotohosting.model.AppUser;
import com.company.springbootphotohosting.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {




}
