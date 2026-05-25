package com.hellmetz.festival.config;


import com.hellmetz.festival.service.UtilisateurDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean  // Ce Bean remplace votre AuthenticationFilter
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Ressources publiques : CSS, JS, images
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/login").permitAll()

                        // acces au back office que pr les admins
                        .requestMatchers("/festival/**").hasAnyRole("ADMIN")

                        // acces au reste des ressource que si on est authentifier
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")      // Votre page de login Thymeleaf
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll());

        return http.build();
    }

    // Ce Bean remplace votre usage direct de BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // A vous : implémentez UserDetailsService pour charger vos Utilisateur
        .userDetailService(UtilisateurDetailService)
}
