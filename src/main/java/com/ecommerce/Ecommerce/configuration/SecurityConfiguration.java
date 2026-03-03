package com.ecommerce.Ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }


   
    @Bean
    @Order(1)
    SecurityFilterChain adminFilterChain(HttpSecurity http)
            throws Exception {

        http.antMatcher("/admin/**")
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/admin/login")
                        .permitAll()
                        .antMatchers("/admin/**")
                        .hasRole("ADMIN"))

                .formLogin(login -> login
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/loginvalidate")
                        .defaultSuccessUrl("/admin/", true)
                        .failureUrl("/admin/login?error=true"))

                .logout(logout -> logout
                        .logoutRequestMatcher(
                                new AntPathRequestMatcher("/admin/logout"))
                        .logoutSuccessUrl("/admin/login"))

                .exceptionHandling(exception ->
                        exception.accessDeniedPage("/403"))

                .csrf(csrf -> csrf.disable());

        return http.build();
    }



    // USER SECURITY
    @Bean
    @Order(2)
    SecurityFilterChain userFilterChain(HttpSecurity http)
            throws Exception {

        http.authorizeHttpRequests(auth -> auth
                .antMatchers(
                        "/login",
                        "/register",
                        "/newuserregister")
                .permitAll()

                .antMatchers("/**")
                .hasRole("USER"))

        .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/userloginvalidate")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true"))

        .logout(logout -> logout
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login"))

        .exceptionHandling(exception ->
                exception.accessDeniedPage("/403"))

        .csrf(csrf -> csrf.disable());

        return http.build();
    }



    @Bean
    UserDetailsService userDetailsService() {

        return username -> {

            User user =
                    userService.getUserByUsername(username);

            if(user == null){

                throw new UsernameNotFoundException(
                        "User not found");
            }

            String role =
                    user.getRole().equals("ROLE_ADMIN")
                            ? "ADMIN"
                            : "USER";

            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword())
                    .roles(role)
                    .build();
        };
    }



    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}