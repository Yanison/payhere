package itewon.seon.Controller;

import itewon.seon.dto.abType.AbTypeListDto;
import itewon.seon.dto.abType.SelectTypeListDto;
import itewon.seon.dto.accountBook.UpdateAccountBookDto;
import itewon.seon.service.AccountBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/accountbook")
@RequiredArgsConstructor
public class AccountBookApiController{
    private final AccountBookService accountBookService;

        @PostMapping("/create")
        public long createAccountBook(@RequestParam long userSeq){
            System.out.println(userSeq);
            long abSeq = accountBookService.createAccountBook(userSeq);
            log.info("createAccountBook abSeq = {}",abSeq);
            return abSeq;
        }

        @PostMapping("/update")
        public void updateAccountBook(UpdateAccountBookDto updateAccountBookDto){
            try{
                accountBookService.updateAccountBook(updateAccountBookDto);
            }catch (Exception e){
                System.out.println(e);
            }
        }

        @PostMapping("/delete")
        public void deleteAccountBook(@RequestParam long abSeq){
            log.info("deleteAccountBook abSeq = {}",abSeq);
            try{
                accountBookService.deleteAccountBook(abSeq);
            }catch (Exception e){
                System.out.println(e);
            }
        }

        @GetMapping("/selectTypeList")
        public HashMap<String,Object> selectTypeList(SelectTypeListDto selectTypeListDto){
            HashMap<String,Object> response = new HashMap<String,Object>();
            List<AbTypeListDto> selectTypeList = accountBookService.SelectTypeList(selectTypeListDto);
            response.put("list",selectTypeList);
            return response;
        }
}
