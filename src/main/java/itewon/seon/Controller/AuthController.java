package itewon.seon.Controller;

import itewon.seon.dto.security.TokenDto;
import itewon.seon.dto.user.LoginRequest;
import itewon.seon.jwt.JwtFilter;
import itewon.seon.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }


    /**
     * 1.HttpRequest -> /authenticate, AuthController.authorize()
     * 로그인 요청 정보가 들어오면
     */
    @PostMapping("/member/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginRequest loginRequest) {
        /**
         *
         *
         * 2.authenticationManagerBuilder -> authenticate(authenticationToken)
         * 유저의 로그인 정보(아이디 비밀번호)를 파라미터로 받는 UsernamePasswordAuthenticationToken객체를 생성하고
         * authenticationManager에게 넘겨준다.
         *
         *
         */
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getUserPw());

        /**
         * 3.CustomUserDetailsService.loadUserByUserName() -> return new Authentication
         * 전달받은 UsernamePasswordAuthenticationToken은
         * authenticationManager가 authenticate메소드에 전달하면
         * CustomUserDetailsService에서 loadUserByUserName을 실행하여
         * DB에서 유저 정보를 조회한다.
         *
         * 4.CustomUserDetailsService.loadUserByUserName()
         * loadUserByUserName에서 인증정보를 거치고 Authentication객체를 반환하게 되면
         */
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
