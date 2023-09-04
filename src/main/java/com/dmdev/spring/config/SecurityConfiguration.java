package com.dmdev.spring.config;

import com.dmdev.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableMethodSecurity // -> need for @PreAuthorize start work
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

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
                        .defaultSuccessUrl("/users"));
//                .oauth2Login(config -> config
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/users")
//                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oAuth2UserService()))
//                );
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {

        return userRequest -> {
            String email = userRequest.getIdToken().getClaim("email");
            // TODO: create user userService.create()
            UserDetails userDetails = userService.loadUserByUsername(email);
//            new OidcUserService().loadUser()
            DefaultOidcUser oidcUser = new DefaultOidcUser(userDetails.getAuthorities(), userRequest.getIdToken());
//            return new DefaultOidcUser(null, null);

            Set<Method> userDetailsMethods = Set.of(UserDetails.class.getMethods());
            return (OidcUser) Proxy.newProxyInstance(SecurityConfiguration.class.getClassLoader(),
                    new Class[]{UserDetails.class, OidcUser.class}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            return userDetailsMethods.contains(method)
                                    ? method.invoke(userDetails, args)
                                    : method.invoke(oidcUser, args);
                        }
                    });
        };
    }


}
