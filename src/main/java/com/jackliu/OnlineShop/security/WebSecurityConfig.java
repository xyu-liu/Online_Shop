package com.jackliu.OnlineShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers("/").permitAll()
                        /*.requestMatchers("/resources/**").permitAll()*/
                        .requestMatchers("/myLogin").permitAll()
                        .requestMatchers("/shop/**").permitAll()
                        .requestMatchers("/shop").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(
                form -> form.loginPage("/myLogin").loginProcessingUrl("/authenticateTheUser")
                        .permitAll()

        ).logout(
                logout -> logout.permitAll().logoutSuccessUrl("/")
        );

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer apiStaticResources() {
        return (web) -> web.ignoring().requestMatchers("/css/**")
                .and().ignoring().requestMatchers("/js/**")
                .and().ignoring().requestMatchers("/assets/**")
                .and().ignoring().requestMatchers("/images/**");
    }





}
