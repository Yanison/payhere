package itewon.seon.config;

import itewon.seon.jwt.JwtAccessDeniedHandler;
import itewon.seon.jwt.JwtAuthenticationEntryPoint;
import itewon.seon.jwt.JwtSecurityConfig;
import itewon.seon.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;

    @Bean // 비밀번호를 DB에 그대로 저장하는 회원 정보가 노출 될 수 있으므로 암호화가 필수적으로 필요
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()			//cors 방지
                .csrf().disable()			//csrf 방지
                .formLogin().disable()		//기본 로그인페이지 없애기
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

//                // 세션을 사용하지 않기 때문에 STATELESS로 설정
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
//                .antMatchers(
//                "/api/member/**"
//                        ,"/payhere/login/"
//                        ,"/payhere/createAccount/"
//                        ,"/payhere/"
//                        ,"/css/**"
//                        ,"/js/**"
//                        ,"/img/**","/favicon/ico"
//                ).permitAll()
                .anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
