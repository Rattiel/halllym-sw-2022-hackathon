package com.gana.demo.global.config;

import com.gana.demo.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 * 보안 설정
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception {
        // http.csrf().disable();

        http
            .csrf().ignoringAntMatchers("/h2-console/**", "/file/update/**");

        // h2 DB console
        http
            .headers()
            .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));

        http
            .authorizeHttpRequests()
            .antMatchers("/css/**", "/js/**", "/library/**").permitAll()
            .antMatchers("/fragment").hasRole(Role.ADMIN.toString())
            .antMatchers("/h2-console").hasRole(Role.ADMIN.toString())
            .antMatchers("/shopping/mall/new/create", "/shopping/mall/*/update", "/shopping/mall/*/delete").hasRole(Role.ADMIN.toString())
            .antMatchers("/register").permitAll()
            .anyRequest().permitAll();

        http
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .permitAll();

        http
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .invalidateHttpSession(true)
            .permitAll();

        return http.build();
    }
}