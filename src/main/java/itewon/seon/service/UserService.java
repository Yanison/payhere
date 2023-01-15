package itewon.seon.service;

import itewon.seon.dto.user.CreateAccountDto;
import itewon.seon.dto.user.LoginDto;
import itewon.seon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    Optional<LoginDto> findUserByIdAndPw(LoginDto loginDto){
        return userRepository.findUserByIdAndPw(loginDto);
    }
    @Transactional
    public int createAccount(CreateAccountDto user){
       return userRepository.createAccount(user);
    }


}
