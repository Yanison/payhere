package itewon.seon.service;

import itewon.seon.dto.security.AuthorityDto;
import itewon.seon.dto.user.CreateAccountDto;
import itewon.seon.dto.user.LoginRequest;
import itewon.seon.dto.user.LoginResponse;
import itewon.seon.dto.user.SignInRequest;
import itewon.seon.exception.DuplicateMemberException;
import itewon.seon.exception.NotFoundMemberException;
import itewon.seon.repository.UserRepository;
import itewon.seon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    Optional<LoginResponse> findUserByIdAndPw(LoginRequest loginRequest){
        return userRepository.findUserByIdAndPw(loginRequest);
    }
    @Transactional
    public int createAccount(CreateAccountDto user){
       return userRepository.createAccount(user);
    }


    /**
     * 유저의 회원가입
     */
    @Transactional
    public SignInRequest signup(SignInRequest signInRequest) {

        if (userRepository.findUserByEmail(signInRequest.getUserEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        /**
         * 유저가 첫 회원가입이라면 유저의 권한 정보도 같이 저장
         */
        AuthorityDto authority = AuthorityDto.builder()
                .userEmail(signInRequest.getUserEmail())
                .authorityName("ROLE_USER")
                .build();

        userRepository.insertAuthority(authority);

        User user = (User) User.builder()
                .username(signInRequest.getUserEmail())
                .password(signInRequest.getUserPw())
                .authorities((GrantedAuthority) Collections.singleton(authority))
                .build();

        return userRepository.signIn(user);
    }


    /**
     * 유저와 권한정보를 가져오는 메소드
     */
    @Transactional
    public void getUserWithAuthorities(String email) {
        List<AuthorityDto>  userAuth  = userRepository.selectUserAuthoritiesByUserEmail(email).orElse(null);
        for(AuthorityDto ee : userAuth){
            System.out.println(ee.getAuthorityName());
        }
    }

    /**
     * 현재SecurityContext에 저장된 username의 정보만 가져온다.
     */
    @Transactional
    public void getMyUserWithAuthorities() {
        List<AuthorityDto>  userAuth = SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::selectUserAuthoritiesByUserEmail)
                .orElseThrow(() -> new NotFoundMemberException("Member not found"));
        for(AuthorityDto ee : userAuth){
            System.out.println(ee.getAuthorityName());
        }
    }


}
