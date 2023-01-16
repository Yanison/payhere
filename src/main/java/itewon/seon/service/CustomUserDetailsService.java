package itewon.seon.service;

import itewon.seon.dto.user.CustomUserDetails;
import itewon.seon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Spring Security에서 제공해주는 인증/인가 방식을 구현하기 위해 기존 UserService 로직에 UserDetailsService을  implements 한다.
     * (User의 Dto도 UserDetailsService을 implements 해야함. )
     * UserDetailsService에서 제공해주는 loadUserByUsername 메소드를 구현하여 Spring Security의 인증 인가 방식을 진행.
     *
     *
     */

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        /**
         * AuthController 에서 authenticationManager가
         * 생성한 토큰으로 authenticate메소드를 실행할때
         * loadUserByUsername메소드가 이행이 된다.
         *
         * 반환값으로 Authentication 객체를 생성한다.
         */
        return userRepository.findUserByEmail(username)
                .map(user -> createUser(username))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(String username) {
        Optional<CustomUserDetails> user = userRepository.findUserByEmail(username);
        /**
         * isEnabled :: DB에서 User의 DelNy를 여부를 판단하고 유저가 가진 정보를 기반으로
         * User(Spring Security) 객체를 반환한다.
         */
        if (!user.get().isEnabled()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = user.get().getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(user.get().getAuthorityName()))
                .collect(Collectors.toList());

        return new User(user.get().getUsername(),
                user.get().getPassword(),
                grantedAuthorities);
    }
}
