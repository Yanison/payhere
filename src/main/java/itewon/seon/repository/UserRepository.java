package itewon.seon.repository;

import itewon.seon.dto.security.AuthorityDto;
import itewon.seon.dto.user.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {
    Optional<LoginResponse> findUserByIdAndPw(LoginRequest loginRequest);
    Optional<CustomUserDetails> findUserByEmail(String email);


    int createAccount(CreateAccountDto user);

    AuthorityDto insertAuthority(AuthorityDto authorityDto);
    Optional<List<AuthorityDto>> selectUserAuthoritiesByUserEmail(String userEmail);
    SignInRequest signIn(User user);
}
