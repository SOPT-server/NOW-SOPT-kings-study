package com.example.seojin.config.auth;

import com.example.seojin.domain.enums.Role;
import com.example.seojin.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(
            (csrfConfig) -> csrfConfig.disable()
        )
        .headers(
            (headerConfig) -> headerConfig.frameOptions(
                frameOptionsConfig -> frameOptionsConfig.disable()
            )
        )
        .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
            .requestMatchers("/login").hasRole(Role.USER.name())
            .requestMatchers("/home", "/css/**", "images/**", "/js/**", "/login/*", "/logout/*").permitAll()
            .anyRequest().authenticated()
        )
        .logout( // 로그아웃 성공 시 이동
            (logoutConfig) -> logoutConfig.logoutSuccessUrl("/")
        )
        // OAuth2 로그인 기능에 대한 여러 설정
        .oauth2Login(Customizer.withDefaults()); // 아래 코드와 동일한 결과
        /*
                .oauth2Login(
                        (oauth) ->
                            oauth.userInfoEndpoint(
                                    (endpoint) -> endpoint.userService(customOAuth2UserService)
                            )
                );
        */

    return http.build();
  }

}
