package com.company.springbootphotohosting;

import com.company.springbootphotohosting.model.AppUser;
import com.company.springbootphotohosting.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Aby użytkownika można było autoryzować z wykorzystaniem Spring Security musimy
// zaimplementować interfejs UserDetailsService. Następnie w klasie implementującej przekazujemy
// użytkownika z repozytorium z wykorzystaniem metody loadUserByUsername

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepo appUserRepo;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(s);       // zwroce obiekt AppUser, a jest on rowniez UserDetails (AppUser implements UserDetails)
    }


}
