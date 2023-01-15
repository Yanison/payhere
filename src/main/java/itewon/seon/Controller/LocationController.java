package itewon.seon.Controller;

import itewon.seon.dto.accountBook.SelectAccountBookDto;
import itewon.seon.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/payhere")
@RequiredArgsConstructor
public class LocationController {
    private final AccountBookService accountBookService;
    public void session(HttpSession session, Model model){
        Object userSeq = session.getAttribute("userSeq");
        Object userName = session.getAttribute("userName");
        model.addAttribute("userSeq",userSeq);
        model.addAttribute("userName",userName);
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model){

        session(session,model);

        return "login";
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model){
        session(session,model);
        return "home";
    }

    @GetMapping("/createAccount")
    public String creatAccount(HttpSession session, Model model){
        session(session,model);
        return "createAccount";
    }

    @GetMapping("/accountBook")
    public String accountBook(HttpSession session, Model model,SelectAccountBookDto selectAccountBookDto){
        session(session,model);
        if(model.getAttribute("userSeq") != null){
            /**
             * %세션% Jwt 구현이후 수정 필요
             */
            selectAccountBookDto.setUserSeq((long) model.getAttribute("userSeq"));
            selectAccountBookDto.setDelNy(0);
            List<SelectAccountBookDto> selectMyAccountBook = accountBookService.selectMyAccountBook(selectAccountBookDto);
            model.addAttribute("selectMyAccountBook",selectMyAccountBook);
            model.addAttribute("nowPage","accountBook");
        }
        return "accountBook";
    }

    @GetMapping("/accountBook/trash")
    public String trash(HttpSession session, Model model,SelectAccountBookDto selectAccountBookDto){
        session(session,model);
        if(model.getAttribute("userSeq") != null){
            /**
             * %세션% Jwt 구현이후 수정 필요
             */
            selectAccountBookDto.setUserSeq((long) model.getAttribute("userSeq"));
            selectAccountBookDto.setDelNy(1);
            List<SelectAccountBookDto> selectMyAccountBook = accountBookService.selectMyAccountBook(selectAccountBookDto);
            model.addAttribute("selectMyAccountBook",selectMyAccountBook);
            model.addAttribute("nowPage","trash");
        }
        return "accountBook";
    }
}
