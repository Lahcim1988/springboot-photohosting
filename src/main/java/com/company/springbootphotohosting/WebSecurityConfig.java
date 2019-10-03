package com.company.springbootphotohosting;

import com.company.springbootphotohosting.model.AppUser;
import com.company.springbootphotohosting.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication().withUser(         // przechowywanie w pamieci - zamieniam na baze danych
//                new User("Mike", passwordEncoder().encode("Mike123"), Collections.singleton(new SimpleGrantedAuthority("user"))));      // definiuje nowego uzytkownika
        auth.userDetailsService(userDetailsService);        // musze przekazac ze klasa odpowiedzialna za laczenie sie z baza danych to wlasnie jest userDetailsService
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1")  // definiuje ktore elementy beda mialy dostep, metoda bedzie dostepna tylko dla uzytkownikow ktorzy maja okreslona role lub sa np zalogowani
                //.authenticated()    // tylko ci ktorzy sa zalogowani
                .hasRole("USER")        // dostep ma tylko USER
                .antMatchers("/test2").hasRole("ADMIN")
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
 /*               .headers().frameOptions().disable()
                .and()
                .headers().disable();*/
  //              .formLogin()
//                .permitAll();       // do loginu beda mialy dostep wszytkie oosoby nie potrzebne jest zalogowanie
    }

    @Bean
    public PasswordEncoder passwordEncoder() {       // zarzadzanie haslami - zaraz po zalogowaniu, sprawdz hasla - nie wyskoczy nam 500
        return new BCryptPasswordEncoder();
    }

    // twoze nowego uzytkownika,
    @EventListener(ApplicationReadyEvent.class)     // pierwsza metoda ktora sie wywola po uruchomieniu programu
    public void get() {
        AppUser appUserUser = new AppUser("UserMike", passwordEncoder().encode("Mike123"), "ROLE_USER");     // haslo trafi do bazy danych zaszyfrowane // # hasla
        AppUser appUserAdmin = new AppUser("AdminMike", passwordEncoder().encode("Mike123"), "ROLE_ADMIN");
        appUserRepo.save(appUserUser);          // wypycham uzytkownika do bazy danych
        appUserRepo.save(appUserAdmin);
    }
}
