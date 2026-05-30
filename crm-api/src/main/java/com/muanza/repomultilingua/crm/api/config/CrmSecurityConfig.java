package com.muanza.repomultilingua.crm.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.muanza.repomultilingua.crm.api.security.JwtAuthenticationFilter;
import com.muanza.repomultilingua.crm.api.security.JwtService;

@Configuration
public class CrmSecurityConfig {

    @Bean
    SecurityFilterChain crmSecurityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/api/v1/auth/login").permitAll()
                        .antMatchers("/api/v1/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    UserDetailsService crmUserDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("crmadmin").password(passwordEncoder.encode("admin123")).roles("ADMINISTRADOR_CRM").build(),
                User.withUsername("parceiro").password(passwordEncoder.encode("parceiro123")).roles("PARCEIRO").build());
    }

    @Bean
    PasswordEncoder crmPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager crmAuthenticationManager(UserDetailsService crmUserDetailsService, PasswordEncoder crmPasswordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(crmUserDetailsService);
        provider.setPasswordEncoder(crmPasswordEncoder);
        return new ProviderManager(provider);
    }

    @Bean
    JwtService crmJwtService() {
        return new JwtService("crm-agt-chave-secreta-2026-segura");
    }

    @Bean
    JwtAuthenticationFilter crmJwtAuthenticationFilter(JwtService crmJwtService, UserDetailsService crmUserDetailsService) {
        return new JwtAuthenticationFilter(crmJwtService, crmUserDetailsService);
    }
}

