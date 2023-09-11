package com.example.SimpleLibAppWithSpringBoot.config;


import com.example.SimpleLibAppWithSpringBoot.repositories.PeopleRepository;
import com.example.SimpleLibAppWithSpringBoot.services.PeopleService;
import com.example.SimpleLibAppWithSpringBoot.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig{

    PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http

                    .csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("auth/login","auth/registration", "/error")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/auth/login")
                    .loginProcessingUrl("/process_page").defaultSuccessUrl("/main", true)
                    .failureUrl("/auth/login?error")
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login");

                    http.authenticationProvider(authenticationProvider());
                    http.headers().frameOptions().sameOrigin();
            return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(personDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

}
