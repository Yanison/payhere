package itewon.seon.repository;

import itewon.seon.dto.user.CreateAccountDto;
import itewon.seon.dto.user.LoginDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserRepository {
    Optional<LoginDto> findUserByIdAndPw(LoginDto loginDto);
    int findUserByEmail(String email);
    int createAccount(CreateAccountDto user);
}
