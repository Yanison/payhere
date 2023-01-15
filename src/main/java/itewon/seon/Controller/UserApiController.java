package itewon.seon.Controller;


import itewon.seon.dto.user.CreateAccountDto;
import itewon.seon.dto.user.LoginDto;
import itewon.seon.repository.UserRepository;
import itewon.seon.service.MailService;
import itewon.seon.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class UserApiController {
    private final MailService mailService;
    private final UserService userService;
    private final UserRepository userRepository;
    @PostMapping("/login")
    public HashMap<String,String> login(LoginDto loginDto, HttpSession session) {

        HashMap<String,String> response = new HashMap<String,String>();
        log.info("user email = {}",loginDto.getUserEmail());
        try{
            Optional<LoginDto> foundUser = userRepository.findUserByIdAndPw(loginDto);
            log.info("userseq = {}", foundUser.get().getUserSeq());
            if(foundUser.get().getUserSeq() != 0){
                session.setAttribute("userSeq",foundUser.get().getUserSeq());
                session.setAttribute("userName",foundUser.get().getUserName());
                session.setMaxInactiveInterval(3600);
                System.out.println("sess"+session.getAttribute("userSeq"));
                response.put("message", "ok");
                return response;
            }else {
                response.put("message", "notFound");
                return response;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
            response.put("message",e.getMessage());
           return response;
        }
    }

    @PostMapping("/createAccount")
    public void createAccount(CreateAccountDto user, HttpServletResponse response){
        try {
            userService.createAccount(user);
            response.sendRedirect("/payhere/");
        }catch (IOException io){
            System.out.println(io);
            io.printStackTrace();
        }
    }

    @PostMapping("/reqeustEmailValidation")
    public HashMap<String,String> reqeustEmailValidation(@RequestParam String userEmail){
        HashMap<String,String> response = new HashMap<String,String>();
        log.info("@RequestParam userEmail = {}",userEmail);
        int foundUser = userRepository.findUserByEmail(userEmail);
        log.info("user foundUser = {}",foundUser);
        if(foundUser != 0){
            response.put("response","duplicatedEmail");
            return response;
        }else {
            response.put("response",mailService.sendMailViaSmtpGmail(userEmail));
            return response;
        }

    }
    @PostMapping("/logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "test";
    }
}
