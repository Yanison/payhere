package itewon.seon.Controller;


import itewon.seon.dto.httpResponse.ErrorCode;
import itewon.seon.dto.httpResponse.HttpResponseMessage;
import itewon.seon.dto.user.CreateAccountDto;
import itewon.seon.dto.user.CustomUserDetails;
import itewon.seon.dto.user.LoginRequest;
import itewon.seon.dto.user.LoginResponse;
import itewon.seon.jwt.TokenProvider;
import itewon.seon.repository.UserRepository;
import itewon.seon.service.MailService;
import itewon.seon.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class UserApiController {
    private final MailService mailService;
    private final UserService userService;
    private final UserRepository userRepository;



    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<HttpResponseMessage> login(LoginRequest loginRequest, HttpSession session, HttpServletResponse httpServletResponse) {
        try{
            Optional<LoginResponse> foundUser = userRepository.findUserByIdAndPw(loginRequest);
            if(foundUser.isPresent() && foundUser.get().getCnt() != 0){
                session.setAttribute("userSeq",foundUser.get().getUserSeq());
                session.setAttribute("userName",foundUser.get().getUserName());
                session.setMaxInactiveInterval(3600);
                return new ResponseEntity<>(
                        HttpResponseMessage
                            .builder()
                                .responseMessage("ok")
                                .data(
                                    LoginResponse
                                    .builder()
                                        .userSeq(foundUser.get().getUserSeq())
                                        .userName(foundUser.get().getUserName())
                                        .userEmail(foundUser.get().getUserEmail())
                                    .build()
                                )
                            .build()
                        ,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(
                        HttpResponseMessage
                            .builder()
                                .responseMessage("로그인 정보와 일치하는 정보가 없습니다.")
                                .errorStatus(String.valueOf(ErrorCode.LOGIN_INPUT_INVALID))
                            .build()
                        ,HttpStatus.NOT_FOUND)
                        ;
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(
                    HttpResponseMessage
                        .builder()
                            .errorStatus(String.valueOf(ErrorCode.METHOD_NOT_ALLOWED))
                        .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR)
                    ;
        }
    }

    @PostMapping("/createAccount")
    public ResponseEntity<HttpResponseMessage> createAccount(CreateAccountDto user, HttpServletResponse response){

        try {
            userService.createAccount(user);
            response.sendRedirect("/payhere/");
            return new ResponseEntity<>(
                    HttpResponseMessage
                        .builder()
                            .responseMessage("회원가입에 성공하셨습니다.")
                        .build()
                    ,HttpStatus.OK
                    );
        }catch (IOException io){
            System.out.println(io);
            io.printStackTrace();
            return new ResponseEntity<>(
                    HttpResponseMessage
                        .builder()
                            .errorStatus(String.valueOf(ErrorCode.METHOD_NOT_ALLOWED))
                        .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @PostMapping("/reqeustEmailValidation")
    public ResponseEntity<HttpResponseMessage> reqeustEmailValidation(@RequestParam String userEmail){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("aplication","json", Charset.forName("UTF-8")));
        Optional<CustomUserDetails> foundUser = userRepository.findUserByEmail(userEmail);
        if(foundUser.get().getCnt() != 0){
            return new ResponseEntity<>(
                    HttpResponseMessage
                    .builder()
                        .errorStatus(ErrorCode.EMAIL_DUPLICATION.name())
                    .build()
                    ,HttpStatus.CREATED
            );
        }else {
            return new ResponseEntity<>(
                    HttpResponseMessage
                    .builder()
                        .responseData(mailService.sendMailViaSmtpGmail(userEmail))
                    .build()
                    ,HttpStatus.OK
            );
        }
    }
    @PostMapping("/logOut")
    public ResponseEntity<HttpResponseMessage> logOut(HttpSession session){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("aplication","json", Charset.forName("UTF-8")));
        session.invalidate();
        return new ResponseEntity<>(
                HttpResponseMessage
                    .builder()
                    .responseMessage("로그아웃에 성공하셨습니다.")
                    .build()
                ,HttpStatus.OK
        );
    }
}
