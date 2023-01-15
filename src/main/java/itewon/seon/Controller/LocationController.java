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
    public String accountBook(HttpSession session, Model model){
        session(session,model);
        if(model.getAttribute("userSeq") != null){
            List<SelectAccountBookDto> selectMyAccountBook = accountBookService.selectMyAccountBook((long) model.getAttribute("userSeq"));
            model.addAttribute("selectMyAccountBook",selectMyAccountBook);
        }
        return "accountBook";
    }
}
