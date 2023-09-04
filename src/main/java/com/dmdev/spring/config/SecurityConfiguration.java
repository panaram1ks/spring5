package com.dmdev.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(
                        urlConfig -> urlConfig
                                .antMatchers("/login", "/users/registration", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                .antMatchers("/users/{\\d+}/delete").hasAuthority("ADMIN")
                                .antMatchers("/admin/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
//                .anyRequest().authenticated()
//                .and()
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")// -> default behave!
                )
//                .httpBasic(Customizer.withDefaults());
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users")
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
